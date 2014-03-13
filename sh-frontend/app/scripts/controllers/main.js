'use strict';

angular.module('shFrontendApp')

.controller('MainCtrl', ['$scope', '$modal', 'photos',
	function($scope, $modal, photos) {
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
		
		// Open the modal image viewer
		var openPhoto = function(photo) {
			
			var modalInstance = $modal.open({
				templateUrl: 'views/PhotoView.html',
				controller: ModalPhotoCtrl,
				resolve: {
					photo: function() {
						return photo;
					}
				}
			});
		};
		$scope.openPhoto = openPhoto;
	}
])

var ModalPhotoCtrl = function($scope, $modalInstance, photo, imageBaseUrl) {
	
	$scope.photo = photo;
	$scope.imageBaseUrl = imageBaseUrl;
	
};

ModalPhotoCtrl['$inject'] = ['$scope', '$modalInstance', 'photo', 'imageBaseUrl'];