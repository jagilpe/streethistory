'use strict';

angular.module('shFrontend.services.photos', ['ngResource'])

.factory('Photo', ['$resource', 'restfulBaseUrl',
	function($resource, restfulBaseUrl) {
		return $resource(restfulBaseUrl + '/photo/:id', {
			id: '@id'
		});
	}
])

.factory('MultiPhotoLoader', ['Photo', '$q',
	function(Photo, $q) {
		return function() {
			var delay = $q.defer();
			Photo.query(function(photos) {
				delay.resolve(photos);
			}, function() {
				delay.reject('Unable to fetch photos');
			});
			return delay.promise;
		};
	}
]);