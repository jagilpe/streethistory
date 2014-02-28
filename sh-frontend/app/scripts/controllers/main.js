'use strict';

angular.module('shFrontendApp')

.controller('MainCtrl', ['$scope', 'photos',
	function($scope, photos) {
		// map initialization
		$scope.map = {
			center: {
				latitude: 52.5162198,
				longitude: 13.3776884
			},
			zoom: 13,
			bounds: {},
			options: {
				streetViewControl: false,
				panControl: false,
				mapTypeControl: false,
				zoomControl: false
			}
		};
		
		// Photos data loading
		$scope.photos = photos;
	}
]);

