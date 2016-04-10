  // Validation de la fenetre d'ajout
//  document.getElementById('btnValiderAjout').onclick = function () { alert('TOTO');}
$(document).ready(function() {
    $('btnValiderAjout').click(function() { alert('TOTO2');});
});

  function afficherMessageOK (message) {
    var outputAlertDiv = document.getElementById('outputAlert');
    outputAlertDiv.style.visibility = 'visible';
    outputAlertDiv.className = 'alert alert-success';
    outputAlertDiv.innerHTML = message ;
  }

  function afficherMessageErreur(message) {
    var outputAlertDiv = document.getElementById('outputAlert');
    outputAlertDiv.style.visibility = 'visible';
    outputAlertDiv.className = 'alert alert-danger';
    outputAlertDiv.innerHTML = '<b>Error Code: </b>' + response.error.code + ' [' + response.error.message + ']';
  }

    function validerAjout() {
        alert('test') ;
        var name = document.getElementById('nom').value;
        var annee = document.getElementById('annee').value;

        var vin = {'nom' : name, 'annee' : annee } ;
        appelleWSCreationVin(vin).then(function(response) {
            ajouterTableau(vin) ;
            }, function(reason) {
            afficherMessageErreur(reason) ;
            }) ;

        return false;
    }

    // This is called initially
    function initIndex() {

        initConstantes() ;

        appelleWSRecuperationVins().then(function(response) {
            afficherMessageOK('Liste chargée (' + response.result.items.length + ')') ;
            jQuery.each( response.result.items, function(i, field) {
                ajouterTableau(field) ;
            }) ;
        }, function (reason) {
            afficherMessageErreur(reason) ;
        }) ;
    }


