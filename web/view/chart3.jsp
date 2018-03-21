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
function display(input1, input2){
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Browser market shares. January, 2018'
	    },
	    subtitle: {
	        text: 'Click the columns to view versions. Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>'
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: 'Total percent market share'
	        }

	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: '{point.y:.1f}'
	            }
	        }
	    },

	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	    },

	    "series": input1,
	    "drilldown": {
	        "series": input2
	    }
	});
}

$(document).ready(function(){
	// AJAX·Î
	$.ajax({
		url : 'chart3impl.do',
		success:function (data) {
			display(data);
			
			$.ajax({
				url : 'chart3impl2.do',
				success:function (data2) {
					display(data, data2);
					
				},
				error:function() {
					alert('fail');
				}
			});
		},
		error:function() {
			alert('fail');
		}
	});
	
});

</script>
<div id="main_center">
<h1>Main Center</h1>
<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

</div>







