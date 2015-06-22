/**
 * Created by KrisViceral on 15/06/2015.
 */
(function() {

    var HomeController = function ($scope, $log, RequestFactory) {
        $scope.requests = [];
        $scope.visitors = [];
        $scope.chartInitialized = false;

        function init(){
            RequestFactory.getRequests()
                .success(function(requests) {
                    $scope.requests = requests;
                })
                .error(function(data, status, headers, config) {
                    $log.log(data.error + ' ' + status);
                });
        }


        $scope.initChart = function(){

            var chartData = initChartData();
            var labelHr = initLabels();
            var dataset = initDataset();
            var requestPerHr = initReqPerHr();

            chartData.labels = labelHr;
            dataset.label= $scope.requests[0].date.year;

            plotRequests(requestPerHr);

            dataset.data = requestPerHr;
            chartData.datasets.push(dataset);

            // Get the context of the canvas element we want to select
            var ctx = document.getElementById("myChart").getContext("2d");

            //initialize chart given our data
            var myBarChart = new Chart(ctx).Bar(chartData, {
                responsive: true,
                maintainAspectRatio: false
            });

            $scope.chartInitialized = true;
        }

        function initChartData() {
            var data = {
                labels: [],
                datasets: []
            };
            return data;
        }

        function initLabels() {
            var labelHr = [];
            for (var i = 0; i < 24; i++) {
                labelHr.push(i + "H");
            }
            return labelHr;
        }

        function initDataset() {
            var defaultDataset = {
                label: "",
                fillColor: "rgba(151,187,205,0.5)",
                strokeColor: "rgba(151,187,205,0.8)",
                highlightFill: "rgba(151,187,205,0.75)",
                highlightStroke: "rgba(151,187,205,1)",
                data: []
            };
            return defaultDataset;
        }

        function initReqPerHr() {
            var requestPerHr = [];
            for (var i = 0; i < 25; i++) {
                requestPerHr.push(0);
            }
            return requestPerHr;
        }

        function plotRequests(requestPerHr) {
            for (var i = 0; i < $scope.requests.length; i++) {
                var request = $scope.requests[i];
                requestPerHr[request.date.hour] = request.totalUniqueVisitors;
            }
        }

        init();
    };

    HomeController.$inject = ['$scope', '$log', 'RequestFactory'];
    angular.module('analyticsApp').controller('HomeController', HomeController);

}());