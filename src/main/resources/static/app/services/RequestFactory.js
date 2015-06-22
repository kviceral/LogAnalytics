/**
 * Created by KrisViceral on 18/06/2015.
 */
(function() {
    var RequestFactory = function($http) {

        var factory = {};

        factory.getRequests = function() {
            return $http.get('/api/requests');
        };

        return factory;
    };

    RequestFactory.$inject = ['$http'];
    angular.module('analyticsApp').factory('RequestFactory', RequestFactory);
}());