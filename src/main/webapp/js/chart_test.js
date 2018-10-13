function dialogBaseData(data, p_div_w) {

	var option = {
		dataset : data,
		grid : {
			width : p_div_w * 1.36 + 'px',
			height : '285px',
			top : 40,
			left : '0%'
		},
		xAxis : {
			type : 'category',
			show : true,
			splitLine : {
				show : false,
				interval : 0,
				lineStyle : {
					width : 1,
					type : 'dotted',
					color : '#ffb5ae'
				}
			},
			axisLabel : {
				show:false,
				margin : 120,
				color : '#ffffff',
				fontSize : 12,
				align : 'left',
				interval : 0,
				inside : true,
				padding : [ 0, 0, 0, 10 ]
			},
			axisTick : {
				show : true,
				alignWithLabel : true,
				interval : 0,
				length : 400,
				inside : true,
				color : '#ffb5ae',
				lineStyle : {
					type : 'dotted'
				}
			},
			axisLine : {
				show : false,
				lineStyle : {
					color : '#ffb5ae'
				}
			}
		},
		yAxis : {
			type : 'value',
			boundaryGap : [ '1', '0%' ],
			splitLine : {
				show : false,
				interval : 0,
				lineStyle : {
					width : 1,
					type : 'dotted',
					color : '#ffb5ae'
				}
			},
			axisLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				show : false
			}
		},
		series : [ {
			seriesLayoutBy : "row",
			type : 'line',
			smooth : true,
			smoothMonotone : 'x',
			color : '#ffffff',
			symbol : 'circle',
			symbolSize : 10,
			label : {
				show : true,
				formatter : '{@负重} kg',
				position : [ 10, -30 ],
				color : '#ff776a',
				fontSize : 14,
				shadowColor : '#999',
				shadowBlur : 5,
				borderRadius : 20,
				padding : [ 5, 10, 5, 10 ],
				backgroundColor : '#ffffff',
			},
			itemStyle : {
				color : "#ff776a",
				borderColor : '#ffffff',
				borderWidth : '2'
			},
			lineStyle : {
				color : '#ffffff',
				shadowColor : '#999',
				shadowBlur : 5
			}
		}, {
			seriesLayoutBy : "row",
			type : 'line',
			smooth : true,
			smoothMonotone : 'x',
			color : '#ffffff',
			symbol : 'circle',
			symbolSize : 10,
			label : {
				show : true,
				formatter : '{@次数}',
				position : [ -10, 20 ],
				color : '#ff776a',
				fontSize : 14,
				shadowColor : '#999',
				shadowBlur : 5,
				borderRadius : 20,
				padding : [ 5, 10, 5, 10 ],
				backgroundColor : '#ffffff',
			},
			itemStyle : {
				color : "#ff776a",
				borderColor : '#ffffff',
				borderWidth : '2'
			},
			lineStyle : {
				color : '#ffffff',
				shadowColor : '#999',
				shadowBlur : 5,
				type : 'dotted'
			}
		} ],
		backgroundColor : '#ff776a',
	};
	return option;
}
function updateData(chart, data) {

	chart.setOption({
		dataset : data
	});
}
function addData(chart, option, date, weight, num) {

	var data = option.dataset;
	data.source[0].push(date);
	if (weight != null) {
		data.source[1].push(weight);
	}
	if (num != null) {
		data.source[2].push(num);
	}
	updateData(chart, data);
}
