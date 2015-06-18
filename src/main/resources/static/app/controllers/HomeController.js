/**
 * Created by KrisViceral on 15/06/2015.
 */
(function() {

    var HomeController = function ($scope, $log, RequestFactory) {
        $scope.request = [];

        function init(){
            RequestFactory.getRequest()
                .success(function(request) {
                    $scope.request = request;
                })
                .error(function(data, status, headers, config) {
                    $log.log(data.error + ' ' + status);
                });
        }

        init();
    };

    HomeController.$inject = ['$scope', '$log', 'RequestFactory'];
    angular.module('analyticsApp').controller('HomeController', HomeController);

}());