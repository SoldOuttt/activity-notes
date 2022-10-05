<#import '../macros/page_tmp.ftlh' as page_tmp>
<#import  '../macros/forms_tmp.ftlh' as forms>

<@page_tmp.page>
    <p>
        <a class="btn btn-success mt-4"
           data-bs-toggle="collapse"
           href="#getStats" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Get custom statistics
        </a>
    </p>

    <div class="collapse mt-5" id="getStats">
        <@forms.get_form "/statistics/period">
            <div class="row mb-5">
                <div class="col-2">
                    <input type="date" class="form-control" placeholder="date from" name="dateFrom">
                </div>
                <div class="col-2">
                    <input type="date" class="form-control" placeholder="date to" name="dateTo">
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary">Get statistics</button>
                </div>
            </div>
        </@forms.get_form>
    </div>

    <script>
        window.onload = function () {

            var options = {
                animationEnabled: true,
                theme: "light1",
                title: {
                    text: "Statistics for the ${periodStatistics.getDateFrom()} / ${periodStatistics.getDateTo()}",
                    fontStyle: "italic",
                    fontFamily: "tahoma"
                },
                axisX: {
                    valueFormatString: "D MMMM"
                },
                axisY: {
                    title: "Total spent time (in hours)",
                    suffix: "h",
                },
                toolTip: {
                    shared: true
                },
                legend: {
                    cursor: "pointer",
                    verticalAlign: "bottom",
                    horizontalAlign: "left",
                    dockInsidePlotArea: true,
                    itemclick: toogleDataSeries
                },
                data: [{
                    type: "line",
                    showInLegend: true,
                    name: "...",
                    markerType: "square",
                    xValueFormatString: "D MMMM, YYYY",
                    color: "#F08080",
                    yValueFormatString: "#,##0h",
                    dataPoints: [
                        <#list periodStatistics.getInfo() as key, value>
                        {x: new Date(2022, ${key.monthValue - 1}, ${key.dayOfMonth}), y: ${value}},
                        </#list>
//                         { x: new Date(2017, 10, 1), y: 63 },
//                         { x: new Date(2017, 10, 2), y: 69 },
//                         { x: new Date(2017, 10, 3), y: 65 },
//                         { x: new Date(2017, 10, 4), y: 70 },
//                         { x: new Date(2017, 10, 5), y: 71 },
//                         { x: new Date(2017, 10, 6), y: 65 },
//                         { x: new Date(2017, 10, 7), y: 73 },
//                         { x: new Date(2017, 10, 8), y: 96 },
//                         { x: new Date(2017, 10, 9), y: 84 },
//                         { x: new Date(2017, 10, 10), y: 85 },
//                         { x: new Date(2017, 10, 11), y: 86 },
//                         { x: new Date(2017, 10, 12), y: 94 },
//                         { x: new Date(2017, 10, 13), y: 97 },
//                         { x: new Date(2017, 10, 14), y: 86 },
//                         { x: new Date(2017, 10, 15), y: 89 }
                    ]
                }]
            };
            $("#chartContainer").CanvasJSChart(options);

            ////////////////////
            var chart1 = new CanvasJS.Chart("chartContainer1", {
                animationEnabled: true,
                theme: "light1", // "light1", "light2", "dark1", "dark2"
                <#--title: {-->
                <#--    text: "Statistics for the ${periodStatistics.getDateFrom()}/${periodStatistics.getDateTo()}"-->
                <#--},-->
                axisY: {
                    title: "Total spent time (in hours)",
                    suffix: "h",
                    interval: 1
                },
                data: [{
                    type: "column",
                    // showInLegend: true,
                    // legendMarkerColor: "grey",
                    // legendText: "date",
                    dataPoints: [
                        <#list periodStatistics.getInfo() as key, value>
                        {y: ${value}, label: "${key.dayOfMonth} ${key.month}"},
                        </#list>
                    ]
                }]
            });
            chart1.render();

            function toogleDataSeries(e) {
                if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                } else {
                    e.dataSeries.visible = true;
                }
                e.chart.render();
            }

        }
    </script>
    <div id="chartContainer" style="height: 400px; width: 70%;"></div>
    </br>
    <div id="chartContainer1" style="height: 400px; width: 70%;"></div>
    <script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
    <script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>

    <div class="p-2 mb-1 mt-4 bg-warning text-dark" style="width: 50%; border-radius: 20px">
        <i>GENERAL INFORMATION:</i>
    </div>
    <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
        <i>TOTAL SPENT TIME: <b>${periodStatistics.totalSpentTime}</b> hour(s)</i>
    </div>
    <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
        <i>INTERVAL SIZE: <b>${periodStatistics.interval}</b> days</i>
    </div>
    <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
        <i>AVERAGE ACTIVITY TIME PER DAY:
            <b>${periodStatistics.averageHours}</b>
            hour(s)
            <b>${periodStatistics.averageMinutes}</b>
            minute(s)
        </i>
    </div>
    </br>
    </br>
</@page_tmp.page>