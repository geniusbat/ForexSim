function graphShow(trace) {
	TESTER = document.getElementById('graph');
	var config = {responsive: true}
	var data = [trace];
	var layout = { 
	  font: {size: 18},
		width:screen.width/2
	};
	Plotly.newPlot(TESTER, data, layout, config );
}