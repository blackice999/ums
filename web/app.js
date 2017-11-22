var App = App || {};

App.constants = {
	basePathJava: 'http://localhost:8080/',
	basePathWeb: 'http://localhost:63343/'
}

App.utils = App.utils || {};

App.utils.createGrid = function(config) {
	if (!config) {
		throw new error("Cannot initialize the grid", config);
	}
	var dataUrl = config.dataUrl,
		columns = config.columns,
		target = config.target || 'body',
		data,
		generateRow = function(item) {
			var tr = jQuery('<tr>');
			columns.forEach(function(column) {
				var td = jQuery('<td>');
				td.text(item[column]);
				tr.append(td);
			});
			return tr;
		},
		generateHeader = function() {
			var thead = jQuery('<thead>'),
				tr = jQuery('<tr>');
			columns.forEach(function(column) {
				var th = jQuery('<th>');
				th.text(column);
				tr.append(th);
			})
			thead.append(tr);
			return thead;
		},
		generateBody = function() {
			var tbody = $('<tbody>');
			data.forEach(function(item) {
				var row;
				row = generateRow(item);
				tbody.append(row);
			});
			return tbody;
		},
		generateTable = function(){
			var table = $('<table>');
			table.append(generateHeader());
			table.append(generateBody());
			table.addClass('table')
			return table;
		},
		buildGrid = function() {
			var table = generateTable();
			$(target).append(table);
		};

	jQuery.ajax({
		method: "POST",
		data: JSON.stringify({"token": "6"}),
		url: dataUrl,
		success: function(resp) {
			data = resp.items;
			buildGrid();
		},
		error: function(e) {
			console.log(e)
		}
	})
};

function getSearchParameters() {
          var prmstr = window.location.search.substr(1);
          return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
    }

    function transformToAssocArray( prmstr ) {
        var params = {};
        var prmarr = prmstr.split("&");
        for ( var i = 0; i < prmarr.length; i++) {
            var tmparr = prmarr[i].split("=");
            params[tmparr[0]] = tmparr[1];
        }
        return params;
    }

   function traverse(o ) {
       for (i in o) {
           if (!!o[i] && typeof(o[i])=="object") {
               console.log(i, o[i])
               traverse(o[i] );
           }
       }
   }