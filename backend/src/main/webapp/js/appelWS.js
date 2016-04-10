var apiName = 'vins';
var apiVersion = 'v1';
var apiRoot = 'https://' + window.location.host + '/_ah/api';

function initConstantes(){
    if (window.location.hostname == 'localhost'
        || window.location.hostname == '127.0.0.1'
        || ((window.location.port != "") && (window.location.port > 1023))) {
        // We're probably running against the DevAppServer
        apiRoot = 'http://' + window.location.host + '/_ah/api';
    }
}

function appelleWSRecuperationVins() {
    return gapi.client.request({
        'path' : apiRoot + '/' + apiName + '/' + apiVersion + '/vin' }) ;
}

function appelleWSCreationVin(vin) {
    return gapi.client.request({
        'path' : apiRoot + '/' + apiName + '/' + apiVersion + '/vin',
        'method' : 'POST',
         'body' : vin}) ;
}