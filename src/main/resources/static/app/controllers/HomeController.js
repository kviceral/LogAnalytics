/**
 * Created by KrisViceral on 15/06/2015.
 */
(function() {

    var HomeController = function ($scope) {
        alert("at home");
    };

    HomeController.$inject = ['$scope'];
    angular.module('analyticsApp')
        .controller('HomeController', HomeController);

}());