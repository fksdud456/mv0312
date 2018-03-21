<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<style>
#main_center{
	margin:0 20px;
	width:760px;
	height:480px;
	background:white;
}
</style>    
<script>
function display(input){
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'AirLine Delay Count Chart1'
	    },
	    subtitle: {
	        text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
	    },
	    xAxis: {
	        type: 'category',
	        labels: {
	            rotation: -45,
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Population (millions)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: 'Population in 2008: <b>{point.y:.1f} millions</b>'
	    },
	    series: [{
	        name: 'Population',
	        data: input,
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y:.1f}', // one decimal
	            y: 10, // 10 pixels down from the top
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});
}

$(document).ready(function(){
	//AJAX
	$.ajax({
		url:'chart1impl.do',
		// data ? 그냥 변수명 안중요한듯
		success:function(a){
			display(a);
		},
		error:function(){
			alert('fail');
		}
	});
	
});
</script>
<div id="main_center">
<h1>Main Center</h1>
<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

</div>




