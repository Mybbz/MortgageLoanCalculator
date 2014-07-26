<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMC</title>

  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
  </script>
  <script src="resources/js/jquery-1.9.1.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
  <script src="resources/js/jquery.jqplot.js"></script>
  <script src="resources/js/jqplot.canvasTextRenderer.js"></script>
  <script src="resources/js/jqplot.canvasAxisLabelRenderer.js"></script>
  <script src="resources/js/jqplot.canvasAxisTickRenderer.js"></script>
  <script src="resources/js/jqplot.dateAxisRenderer.js"></script>
  <script src="resources/js/jqplot.logAxisRenderer.js"></script>
  <script src="resources/js/jqplot.categoryAxisRenderer.js"></script>
  <script src="resources/js/tinybox.js"></script>


<script>
  $(document).ready(function() {
	$("#itemList").hide();
	$("#summary").hide();
    $("#chart_modal").hide();
	$("#fixedrate").show();
	$("#fixedButton").click(function(){
    	    $("#arm").hide();
    	    $("#itemList").hide();
    	    $("#summary").hide();
    	    $("#chart_modal").hide();
			$("#fixedrate").show();
			
	});
    $("#ARMButton").click(function(){
		$("#fixedrate").hide();
		$("#itemList").hide();
		$("#summary").hide();
		$("#chart_modal").hide();
		$("#arm").show();
    });
    
    $("#clear").click(function(){
    	document.getElementById("1").value = '';
    	document.getElementById("2").value = '';
    	document.getElementById("3").value = '';
    	document.getElementById("4").value = '';
    	$("#itemList").hide();
		$("#summary").hide();
		$("#chart_modal").hide();
    });
    $("#armClear").click(function(){
    	document.getElementById("5").value = '';
    	document.getElementById("6").value = '';
    	document.getElementById("7").value = '';
    	document.getElementById("8").value = '';
    	document.getElementById("9").value = '';
    	$("#itemList").hide();
		$("#summary").hide();
		$("#chart_modal").hide();
    	
    });
    
    $("#submit").click(function() {
		var params=$("input").serialize(); 
		$("#itemList").hide();
		$("#summary").hide();
		$("#chart_modal").hide();
		$.ajax({
			async: false,
			url: "http://localhost:8080/EMC/rest/cal",
			type: "post",
			dataType: "json",
			data: params,
			success:showData
		});
    });
    
    
    $("#armSubmit").click(function() {
		var params=$("input").serialize(); 
		$("#itemList").hide();
		$("#summary").hide();
		$("#chart_modal").hide();
		$.ajax({
			async: false,
			url: "http://localhost:8080/EMC/rest/armcal",
			type: "post",
			dataType: "json",
			data: params,
			success:showData
		});
    });
  }); 
  
  function showData(data) {
	     var rows = "";
	     var rowsSummary="";
	     var plotData=[];
	     var totalPayment=0;
	     var totalInterest=0;
	     var totalPrincipal=0;
	     $("#loan").empty();
	     $("#summaryInterest").empty();
	     $(data.payment).each(function(i, item) {
	       var month = item.month;
	       var beginningBalance = item.beginningBalance;
	       var monthPayment = item.monthPayment;
	       var interest = item.interest;
	       var principal = item.principal;
	       var endingBalance = item.endingBalance;
	       totalPayment=totalPayment+parseFloat(monthPayment);
	       totalInterest=totalInterest+parseFloat(item.interest);
	       totalPrincipal=parseFloat(item.principal)+totalPrincipal;
	       rows = "<tr><td>" + month + "</td><td>" + beginningBalance + "</td><td>" + monthPayment + "</td><td>" + interest + "</td><td>" + principal + "</td><td>"+endingBalance+"</td></tr>";
	       $(rows).appendTo("#loan");
	       plotData.push([parseInt(month),parseFloat(interest)]);
	     });
	     rowsSummary="<tr><td>"+parseInt(totalPayment)+"</td><td>"+parseInt(totalInterest)+"</td><td>"+parseInt(totalPrincipal)+"</td><td>"+parseInt(data.save)+"</td></tr>";
	     $(rowsSummary).appendTo("#summaryInterest");
	     $("#chart_modal").fadeIn("slow");
	     
	     var plot = $.jqplot('chart', [plotData], {
         	seriesColors:['blue'],
         	series:[
         	        {
         	        	lineWidth:1,
         	        	markerOptions:{style:'x'}
         	    
         	        }],
             axes: {
                 xaxis: {
                     label: 'Month',
                     labelRenderer: $.jqplot.CanvasAxisLabelRenderer
                 },
                 yaxis: {
                     label: 'Interest',
                     labelRenderer: $.jqplot.CanvasAxisLabelRenderer
                 }
              }
         });
         plot.replot();
         
         $("#itemList").fadeIn("slow");
         $("#summary").fadeIn("slow");
      };
    
</script>

<style type="text/css">
     #head_img{
     float:right;
     right:2px;
     top: 1px;
     }
  
     
     
    
     
   
  
</style>
</head>



<body>
<h2><font color="green">${title}</font></h2>

<img alt="" src="resources/images/house.jpg" id="head_img" >
<div>
<button id="fixedButton">Fixed rate home loan</button>
<button id="ARMButton">ARM home loan</button>
</div>

<br>
<table id="fixedrate" style="display:none;" class="infotable">
<tr>
<td width="475" >
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15">
Fixed rate home mortgage amount ($):
</td>
<td width="125">
<input type="text" name="principal" size="12" maxlength="12" value="" id="1" />
</td>
</tr>

<tr>
<td width="475" >
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >
Number of loan year:
</td>
<td width="400" class="ChartNumCell">
<input type="radio" name="fixedyear" value="15" id="2">15 year
<input type="radio" name="fixedyear" value="20" id="2">20 year
<input type="radio" name="fixedyear" value="30" id="2">30 year
</td>
</tr>



<tr>
<td width="475">
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >
Additional principal for each month:
</td>
<td width="125">
<input type="text" name="additional_principal" size="12" maxlength="12" value="" id="3" />
</td>
</tr>

<tr>
<td width="475" >
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >
Start month of first extra payment:
</td>
<td width="125" class="ChartNumCell">
<input type="text" id="4" name="start_month"  size="12" maxlength="12" value="" onKeyUp="clear_results(this.form)" />
</td>
</tr>




<tr>
<td>
<button id="clear">Clear</button>
<button id ="submit">Submit</button>
</td>
</tr>
</table>









<table id="arm" style="display:none;" class="infotable">

<tr>
<td width="475" >
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >
Please choose ARM type:
</td>
<td width="400" class="ChartNumCell">
<input type="radio" name="armType" value="5" id="5">5/1 ARM
<input type="radio" name="armType" value="7" id="5">7/1 ARM
<input type="radio" name="armType" value="10" id="5" >10/1 ARM
</td>
</tr>


<tr>
<td width="475">
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >
Adjustable rate home mortgage amount ($):
</td>
<td width="125" >
<input type="text" name="ARM_principal" size="12" maxlength="12" value="" id="6" />
</td>
</tr>


<tr>
<td width="475">
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >
Number of years (#):
</td>
<td width="125">
<input type="text" name="ARM_year" size="6" maxlength="6" value="" id="7"/>
</td>
</tr>



<tr>
<td width="475" >
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help" >

Expected adjustment (%):
</td>
<td width="125" >
<input type="text" name="ARM_adjust_rate" size="6" maxlength="6" value="" id="8" />
</td>
</tr>

<tr >
<td width="475" >
<img src="http://www.free-online-calculator-use.com/image-files/qm.jpg" width="15" height="15" alt="Help">

Interest rate cap (%):
</td>
<td width="125">
<input type="text" name="ARM_rate_cap" size="6" maxlength="6" value="" onKeyUp="clear_results(this.form)" id="9"/>
</td>
</tr>


<tr >
<td>
<button id="armClear" >Clear</button>
<button id="armSubmit">Submit</button>
</td>
</tr>
</table>


<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
<center>
<div class="modal fade" id="chart_modal" style="width:800px;" >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title"><font color="green">Interest distribution for whole term</font></h4>
        </div>
        <div class="modal-body" id="chart">
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</center>



<table id="summary" border="2" width="1200" align="center">
	<tr>
		<th>Total Payment</th>
		<th>Total Interest</th>
        <th>Total Principal</th>
        <th>Total Save</th>
	</tr>
	<tbody id="summaryInterest">
	</tbody>
</table>

<br>
<table id="itemList" border="2" width="1200" align="center">
	<tr>
		<th>Month</th>
		<th>Beginning Balance</th>
		<th>Payment</th>
		<th>Interest</th>
		<th>Principal</th>
		<th>Ending Balance</th>
	</tr>
	<tbody id="loan">
	</tbody>
</table>



</body>
</html>