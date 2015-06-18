/**
 * Created by KrisViceral on 18/06/2015.
 */
(function() {
    var RequestFactory = function($http) {

        var factory = {};

        factory.getRequest= function() {
            return $http.get('/api/request');
        };

        return factory;
    };

    RequestFactory.$inject = ['$http'];
    angular.module('analyticsApp').factory('RequestFactory', RequestFactory);
}());