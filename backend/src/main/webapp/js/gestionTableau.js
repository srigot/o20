function ajouterTableau(vin) {
var str = '<tr>';
str += '<td>' + vin.nom + '</td>' ;
str += '<td>&nbsp;</td>';
str += '</tr>';
$('#table_vins').append(str);
}