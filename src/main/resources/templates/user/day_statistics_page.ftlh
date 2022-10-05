<#import '../macros/page_tmp.ftlh' as page_tmp>
<#import '../macros/forms_tmp.ftlh' as forms>

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

    <#if dayStatistics??>
        <script>
            window.onload = function () {
                <#--    var chart = new CanvasJS.Chart("chartContainer", {-->
                <#--        animationEnabled: true,-->
                <#--        theme: "light2", // "light1", "light2", "dark1", "dark2"-->
                <#--        &lt;#&ndash;title: {&ndash;&gt;-->
                <#--        &lt;#&ndash;    text: "Statistics as of the ${dayStatistics.date}"&ndash;&gt;-->
                <#--        &lt;#&ndash;},&ndash;&gt;-->
                <#--        axisY: {-->
                <#--            title: "Time spent (in hours)",-->
                <#--            minimum: 0,-->
                <#--            interval: 1-->
                <#--        },-->
                <#--        data: [{-->
                <#--            type: "column",-->
                <#--            showInLegend: true,-->
                <#--            legendMarkerColor: "grey",-->
                <#--            legendText: "Subjects",-->
                <#--            dataPoints: [-->
                <#--                <#list dayStatistics.getInfo() as key, value>-->
                <#--                {y: ${value}, label: "${key}"},-->
                <#--                </#list>-->
                <#--            ]-->
                <#--        }]-->
                <#--    });-->
                <#--    chart.render();-->
                /////////////////////////////
                var chart1 = new CanvasJS.Chart("chartContainer1", {
                    exportEnabled: true,
                    animationEnabled: true,
                    title: {
                        text: "Statistics for the ${dayStatistics.date}",
                        fontStyle: "italic",
                        fontFamily: "tahoma"
                    },
                    legend: {
                        cursor: "pointer",
                        itemclick: explodePie
                    },
                    data: [{
                        type: "pie",
                        showInLegend: true,
                        toolTipContent: "{name}: <strong>{y}%</strong>",
                        indexLabel: "{name} - {y} hours - {percent}%",
                        dataPoints: [
                            <#list dayStatistics.getInfo() as key, value>
                            {
                                y: ${value},
                                name: "${key}",
                                percent: parseInt(${value} / ${dayStatistics.totalSpentTime} * 1000) / 10
                            },
                            </#list>
                        ]
                    }]
                });
                chart1.render();
            }

            function explodePie(e) {
                if (typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
                    e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
                } else {
                    e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
                }
                e.chart1.render();
            }
        </script>
        <div id="chartContainer1" style="height: 400px; width: 50%;"></div>
        </br>
        </br>
        </br>
    <#--    <div id="chartContainer" style="height: 300px; width: 50%;"></div>-->
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
            <i>GENERAL INFORMATION:</i>
        </div>
        <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
            <i>TOTAL SPENT TIME: <b>${dayStatistics.totalSpentTime}</b> hour(s)</i>
        </div>
        <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
            <i>NUMBER OF SUBJECTS: <b>${dayStatistics.getInfo()?size}</b></i>
        </div>
        <div class="p-2 mb-1 bg-warning text-dark" style="width: 50%; border-radius: 20px">
            <i>SUBJECT NAMES:&nbsp;
                <#list dayStatistics.getInfo()?keys as key>
                    <b><span style="text-transform:uppercase">${key}&nbsp;&nbsp;</span></b>
                </#list>
            </i>
        </div>
        <#else>
        <div>
            <img src="https://i.ibb.co/hmdky72/image.png"
                    <#--src="https://ltdfoto.ru/images/2022/06/11/photo_2022-06-11_01-19-32.jpg"-->
                 alt=""
                 style="width: 500px; height: 500px"
                 class="rounded-circle"
            />
        </div>
    </#if>
</@page_tmp.page>