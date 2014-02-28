'use strict';

angular.module('shFrontendApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute',
  'ui.bootstrap',
  'google-maps',
	'shFrontend.services.photos'
])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
				resolve: {
					photos: ['MultiPhotoLoader', function(MultiPhotoLoader) {
						return new MultiPhotoLoader();
					}]
				}
      })
      .otherwise({
        redirectTo: '/'
      });
  });
