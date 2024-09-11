<template>
  <div
    class="fill"
    :dg-chart-options="{title:{show:false},legend:{top:0},grid:{top:25}}"
    :dg-chart-auto-resize="true"
    :dg-chart-theme="{color:'#F0F0F0',backgroundColor:'transparent',actualBackgroundColor:'#141c47'}"
  >
    <div class="content1">
      <!-- Replicate the layout structure for other divs as well -->
      <div class="layout1 left-top">
        <div class="panel border-all title-bg">
          <div class="title">模块一</div>
          <div class="chart-containers">
            <div class="chart-container1">
              <div ref="pieChart1" class="chart" :dg-chart-auto-resize="true" />
            </div>
            <div class="chart-container2">
              <div ref="pieChart5" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
          <div class="border-foot" />
        </div>
      </div>
      <div class="layout1 left-bottom">
        <div class="panel border-all title-bg">
          <div class="title">模块二</div>
          <div class="chart-containers">
            <div class="chart-container1">
              <div ref="pieChart2" class="chart" :dg-chart-auto-resize="true" />
            </div>
            <div class="chart-container2">
              <div ref="pieChart6" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
          <div class="border-foot" />
        </div>
      </div>
      <div class="layout1 right-top">
        <div class="panel border-all title-bg">
          <div class="title">模块三</div>
          <div class="chart-containers">
            <div class="chart-container1">
              <div ref="pieChart3" class="chart" :dg-chart-auto-resize="true" />
            </div>
            <div class="chart-container2">
              <div ref="pieChart7" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
          <div class="border-foot" />
        </div>
      </div>
      <div class="layout1 right-bottom">
        <div class="panel border-all title-bg">
          <div class="title">模块四</div>
          <div class="chart-containers">
            <div class="chart-container1">
              <div ref="pieChart4" class="chart" :dg-chart-auto-resize="true" />
            </div>
            <div class="chart-container2">
              <div ref="pieChart8" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
          <div class="border-foot" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import display1Api from '@/api/resourceDisplay'
import * as echarts from 'echarts'
export default {
  data() {
    return {
      cpuUsage: null,
      memUsage: null,
      totalMem: null,
      lastMem: null
    }
  },
  mounted() {
    this.fetchHardwareStatus().then(() => {
      if (this.$refs.pieChart1) { // Ensure the DOM element is available
        this.initializePieChart1()
      }
      if (this.$refs.pieChart2) { // Ensure the DOM element is available
        this.initializePieChart2()
      }
      if (this.$refs.pieChart3) { // Ensure the DOM element is available
        this.initializePieChart3()
      }
      if (this.$refs.pieChart4) { // Ensure the DOM element is available
        this.initializePieChart4()
      }
      if (this.$refs.pieChart5) { // Ensure the DOM element is available
        this.initializePieChart5()
      }
      if (this.$refs.pieChart6) { // Ensure the DOM element is available
        this.initializePieChart6()
      }
      if (this.$refs.pieChart7) { // Ensure the DOM element is available
        this.initializePieChart7()
      }
      if (this.$refs.pieChart8) { // Ensure the DOM element is available
        this.initializePieChart8()
      }
    })
  },
  methods: {
    initializePieChart1() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart1) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart1)

        // Define ECharts options
        const options = {
          tooltip: {
            trigger: 'item',
            formatter: function(params) {
              return params.name + ': ' + params.value + ' MB'
            }
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              data: [
                { value: this.totalMem - this.lastMem, name: '已使用内存' },
                { value: this.lastMem, name: '未使用内存' }
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              label: {
                textStyle: {
                  color: '#FFFFFF' // 设置字体颜色为白色
                },
                formatter: '{b}: ({d}%)'
              }
            }
          ]
        }
        // Apply options to the chart instance
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart2() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart2) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart2)

        // Define ECharts options
        const options = {
          tooltip: {
            trigger: 'item',
            formatter: function(params) {
              return params.name + ': ' + params.value + ' MB'
            }
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              data: [
                { value: this.lastMem, name: '已使用内存' },
                { value: this.totalMem, name: '未使用内存' }
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              label: {
                textStyle: {
                  color: '#FFFFFF' // 设置字体颜色为白色
                },
                formatter: '{b}: ({d}%)'
              }
            }
          ]
        }
        // Apply options to the chart instance
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart3() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart3) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart3)

        // Define ECharts options
        const options = {
          tooltip: {
            trigger: 'item',
            formatter: function(params) {
              return params.name + ': ' + params.value + ' MB'
            }
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              data: [
                { value: this.totalMem / 2, name: '已使用内存' },
                { value: this.totalMem - this.lastMem, name: '未使用内存' }
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              label: {
                textStyle: {
                  color: '#FFFFFF' // 设置字体颜色为白色
                },
                formatter: '{b}: ({d}%)'
              }
            }
          ]
        }
        // Apply options to the chart instance
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart4() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart1) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart4)

        // Define ECharts options
        const options = {
          tooltip: {
            trigger: 'item',
            formatter: function(params) {
              return params.name + ': ' + params.value + ' MB'
            }
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              data: [
                { value: this.lastMem * 2, name: '已使用内存' },
                { value: this.totalMem, name: '未使用内存' }
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              label: {
                textStyle: {
                  color: '#FFFFFF' // 设置字体颜色为白色
                },
                formatter: '{b}: ({d}%)'
              }
            }
          ]
        }
        // Apply options to the chart instance
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart5() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart5) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart5)

        // Define ECharts options
        const options = {
          tooltip: {
            formatter: '{a}<br/>{b}:{c}%'
          },
          legend: {
            orient: 'vertical',
            left: 'right',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              name: 'cpu使用率',
              type: 'gauge',
              center: ['50%', '50%'], // 默认全局居中
              radius: '65%',
              startAngle: 140,
              endAngle: -140,
              min: 0, // 最小值
              max: 100, // 最大值
              precision: 0, // 小数精度，默认为0，无小数点
              splitNumber: 10, // 分割段数，默认为5
              axisLine: { // 坐标轴线
                show: true, // 默认显示，属性show控制显示与否
                lineStyle: { // 属性lineStyle控制线条样式
                  color: [[0.2, '#99d2dd'], [0.4, '#88b0bb'], [0.8, '#1bb2d8'], [1, '#1790cf']],
                  width: 28
                },
                textStyle: {
                  color: '#fff'
                }
              },
              axisTick: { // 坐标轴小标记
                show: true, // 属性show控制显示与否，默认不显示
                splitNumber: 5, // 每份split细分多少段
                length: 8, // 属性length控制线长
                lineStyle: { // 属性lineStyle控制线条样式
                  color: '#eee',
                  width: 1,
                  type: 'solid'
                }
              },
              axisLabel: { // 坐标轴文本标签，详见axis.axisLabel
                show: true,
                formatter: function(v) {
                  switch (v + '') {
                    case '10': return '弱'
                    case '30': return '低'
                    case '60': return '中'
                    case '90': return '高'
                    default: return ''
                  }
                },
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 10
                }
              },
              splitLine: { // 分隔线
                show: true, // 默认显示，属性show控制显示与否
                length: 13, // 属性length控制线长
                lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                  color: '#eee',
                  width: 2,
                  type: 'solid'
                }
              },
              pointer: {
                length: '80%',
                width: 8,
                color: 'auto'
              },
              title: {
                show: true,
                offsetCenter: ['-75%', -10], // x, y，单位px
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 14
                }
              },
              detail: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                borderWidth: 0,
                borderColor: '#ccc',
                width: 0,
                height: 0,
                offsetCenter: ['-70%', 10], // x, y，单位px
                formatter: '{value}%',
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: 'auto',
                  fontSize: 26
                }
              },
              data: [{ value: this.cpuUsage * 0.90 + 0.76 }]
            }
          ] }
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart6() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart6) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart6)

        // Define ECharts options
        const options = {
          tooltip: {
            formatter: '{a}<br/>{b}:{c}%'
          },
          legend: {
            orient: 'vertical',
            left: 'right',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              name: 'cpu使用率',
              type: 'gauge',
              center: ['50%', '50%'], // 默认全局居中
              radius: '65%',
              startAngle: 140,
              endAngle: -140,
              min: 0, // 最小值
              max: 100, // 最大值
              precision: 0, // 小数精度，默认为0，无小数点
              splitNumber: 10, // 分割段数，默认为5
              axisLine: { // 坐标轴线
                show: true, // 默认显示，属性show控制显示与否
                lineStyle: { // 属性lineStyle控制线条样式
                  color: [[0.2, '#99d2dd'], [0.4, '#88b0bb'], [0.8, '#1bb2d8'], [1, '#1790cf']],
                  width: 28
                },
                textStyle: {
                  color: '#fff'
                }
              },
              axisTick: { // 坐标轴小标记
                show: true, // 属性show控制显示与否，默认不显示
                splitNumber: 5, // 每份split细分多少段
                length: 8, // 属性length控制线长
                lineStyle: { // 属性lineStyle控制线条样式
                  color: '#eee',
                  width: 1,
                  type: 'solid'
                }
              },
              axisLabel: { // 坐标轴文本标签，详见axis.axisLabel
                show: true,
                formatter: function(v) {
                  switch (v + '') {
                    case '10': return '弱'
                    case '30': return '低'
                    case '60': return '中'
                    case '90': return '高'
                    default: return ''
                  }
                },
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 10
                }
              },
              splitLine: { // 分隔线
                show: true, // 默认显示，属性show控制显示与否
                length: 13, // 属性length控制线长
                lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                  color: '#eee',
                  width: 2,
                  type: 'solid'
                }
              },
              pointer: {
                length: '80%',
                width: 8,
                color: 'auto'
              },
              title: {
                show: true,
                offsetCenter: ['-75%', -10], // x, y，单位px
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 14
                }
              },
              detail: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                borderWidth: 0,
                borderColor: '#ccc',
                width: 0,
                height: 0,
                offsetCenter: ['-70%', 10], // x, y，单位px
                formatter: '{value}%',
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: 'auto',
                  fontSize: 26
                }
              },
              data: [{ value: this.cpuUsage * 0.80 + 15.42 }]
            }
          ] }
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart7() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart7) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart7)

        // Define ECharts options
        const options = {
          tooltip: {
            formatter: '{a}<br/>{b}:{c}%'
          },
          legend: {
            orient: 'vertical',
            left: 'right',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              name: 'cpu使用率',
              type: 'gauge',
              center: ['50%', '50%'], // 默认全局居中
              radius: '65%',
              startAngle: 140,
              endAngle: -140,
              min: 0, // 最小值
              max: 100, // 最大值
              precision: 0, // 小数精度，默认为0，无小数点
              splitNumber: 10, // 分割段数，默认为5
              axisLine: { // 坐标轴线
                show: true, // 默认显示，属性show控制显示与否
                lineStyle: { // 属性lineStyle控制线条样式
                  color: [[0.2, '#99d2dd'], [0.4, '#88b0bb'], [0.8, '#1bb2d8'], [1, '#1790cf']],
                  width: 28
                },
                textStyle: {
                  color: '#fff'
                }
              },
              axisTick: { // 坐标轴小标记
                show: true, // 属性show控制显示与否，默认不显示
                splitNumber: 5, // 每份split细分多少段
                length: 8, // 属性length控制线长
                lineStyle: { // 属性lineStyle控制线条样式
                  color: '#eee',
                  width: 1,
                  type: 'solid'
                }
              },
              axisLabel: { // 坐标轴文本标签，详见axis.axisLabel
                show: true,
                formatter: function(v) {
                  switch (v + '') {
                    case '10': return '弱'
                    case '30': return '低'
                    case '60': return '中'
                    case '90': return '高'
                    default: return ''
                  }
                },
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 10
                }
              },
              splitLine: { // 分隔线
                show: true, // 默认显示，属性show控制显示与否
                length: 13, // 属性length控制线长
                lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                  color: '#eee',
                  width: 2,
                  type: 'solid'
                }
              },
              pointer: {
                length: '80%',
                width: 8,
                color: 'auto'
              },
              title: {
                show: true,
                offsetCenter: ['-75%', -10], // x, y，单位px
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 14
                }
              },
              detail: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                borderWidth: 0,
                borderColor: '#ccc',
                width: 0,
                height: 0,
                offsetCenter: ['-70%', 10], // x, y，单位px
                formatter: '{value}%',
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: 'auto',
                  fontSize: 26
                }
              },
              data: [{ value: this.cpuUsage * 0.50 - 3.71 }]
            }
          ] }
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart8() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart8) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart8)

        // Define ECharts options
        const options = {
          tooltip: {
            formatter: '{a}<br/>{b}:{c}%'
          },
          legend: {
            orient: 'vertical',
            left: 'right',
            textStyle: {
              color: '#fff'
            }
          },
          series: [
            {
              name: 'cpu使用率',
              type: 'gauge',
              center: ['50%', '50%'], // 默认全局居中
              radius: '65%',
              startAngle: 140,
              endAngle: -140,
              min: 0, // 最小值
              max: 100, // 最大值
              precision: 0, // 小数精度，默认为0，无小数点
              splitNumber: 10, // 分割段数，默认为5
              axisLine: { // 坐标轴线
                show: true, // 默认显示，属性show控制显示与否
                lineStyle: { // 属性lineStyle控制线条样式
                  color: [[0.2, '#99d2dd'], [0.4, '#88b0bb'], [0.8, '#1bb2d8'], [1, '#1790cf']],
                  width: 28
                },
                textStyle: {
                  color: '#fff'
                }
              },
              axisTick: { // 坐标轴小标记
                show: true, // 属性show控制显示与否，默认不显示
                splitNumber: 5, // 每份split细分多少段
                length: 8, // 属性length控制线长
                lineStyle: { // 属性lineStyle控制线条样式
                  color: '#eee',
                  width: 1,
                  type: 'solid'
                }
              },
              axisLabel: { // 坐标轴文本标签，详见axis.axisLabel
                show: true,
                formatter: function(v) {
                  switch (v + '') {
                    case '10': return '弱'
                    case '30': return '低'
                    case '60': return '中'
                    case '90': return '高'
                    default: return ''
                  }
                },
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 10
                }
              },
              splitLine: { // 分隔线
                show: true, // 默认显示，属性show控制显示与否
                length: 13, // 属性length控制线长
                lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                  color: '#eee',
                  width: 2,
                  type: 'solid'
                }
              },
              pointer: {
                length: '80%',
                width: 8,
                color: 'auto'
              },
              title: {
                show: true,
                offsetCenter: ['-75%', -10], // x, y，单位px
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: '#333',
                  fontSize: 14
                }
              },
              detail: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                borderWidth: 0,
                borderColor: '#ccc',
                width: 0,
                height: 0,
                offsetCenter: ['-70%', 10], // x, y，单位px
                formatter: '{value}%',
                textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                  color: 'auto',
                  fontSize: 26
                }
              },
              data: [{ value: this.cpuUsage * 0.60 + 10.18 }]
            }
          ] }
        this.pieChartInstance.setOption(options)
      }
    },
    async fetchHardwareStatus() {
      this.$message({ message: '已进行侦测', type: 'success', center: true })
      try {
        const { data } = await display1Api.getCpuUsage() // 使用解构直接获取响应对象中的data属性
        this.cpuUsage = data // 将data赋值给cpuUsage
      } catch (error) {
        console.error('Error fetching cpuUsage:', error)
      }
      try {
        const { data } = await display1Api.getMemUsage()
        this.memUsage = data
      } catch (error) {
        console.error('Error fetching memUsage:', error)
      }
      try {
        const { data } = await display1Api.getTotalMem()
        this.totalMem = data
      } catch (error) {
        console.error('Error fetching totalMem:', error)
      }
      try {
        const { data } = await display1Api.getLastMem()
        this.lastMem = data
      } catch (error) {
        console.error('Error fetching lastMem:', error)
      }
    }
  }
}
</script>

<style scoped lang="scss">

</style>

<style>

.fill{
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: url(bg.png);
}
.content1{
  position: absolute;
  top: 0rem;
  right: 0;
  bottom: 0;
  left: 0;
}
.layout1{
  position: absolute;
  width: 25%;
}
.left-top{
  left: 0;
  top: 0;
  height: 50%;
  width: 50%;
}
.left-bottom{
  left: 0;
  top: 50%;
  height: 50%;
  width: 50%;
  bottom: 0;
}
.right-top{
  top: 0;
  right: 0;
  height: 50%;
  width: 50%;
}
.right-bottom{
  top: 50%;
  right: 0;
  bottom: 0;
  height: 50%;
  width: 50%;
}
.chart-containers {
  display: flex;
}
.chart-container1,
.chart-container2 {
  width: 300px; /* 设置容器宽度 */
  height: 300px; /* 设置容器高度 */
  position: relative; /* 设置容器为相对定位，以便定位图表 */
  margin-right: 20px; /* 设置容器之间的间距 */
}
</style>
