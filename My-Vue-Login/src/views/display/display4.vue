<template>
  <div
    class="fill1"
    :dg-chart-options="{title:{show:false},legend:{top:0},grid:{top:25}}"
    :dg-chart-auto-resize="true"
    :dg-chart-theme="{color:'#F0F0F0',backgroundColor:'transparent',actualBackgroundColor:'#141c47'}"
  >
    <el-card id="search2">
      <el-row>
        <el-col :span="20">
          <el-input v-model="id" placeholder="模块ID" clearable @input="handleInput" />
          <el-button type="primary" round icon="el-icon-search" @click="fetchHardwareStatusById()">查询</el-button>
        </el-col>
      </el-row>
    </el-card>
    <div class="content">
      <!-- Replicate the layout structure for other divs as well -->
      <div class="layout layout-left-top2">
        <div class="chart-containers">
          <div class="chart-container1">
            <div class="panel border-all title-bg">
              <div class="title">内存使用情况</div>
              <div ref="pieChart1" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
          <div class="chart-container1">
            <div class="panel border-all title-bg">
              <div class="title">CPU使用情况</div>
              <div ref="pieChart2" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
        </div>
      </div>
      <div class="layout layout-center-top2">
        <div class="chart-containers">
          <div class="chart-container1">
            <div class="panel border-all title-bg">
              <div class="title">设备温度变化</div>
              <div ref="pieChart3" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
          <div class="chart-container1">
            <div class="panel border-all title-bg">
              <div class="title">网络连接速度</div>
              <div ref="pieChart4" class="chart" :dg-chart-auto-resize="true" />
            </div>
          </div>
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
      lastMem: null,
      id: 1
    }
  },
  mounted() {
    this.fetchHardwareStatusById().then(() => {
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
    initializePieChart3() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart3) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart3)

        // Define ECharts options
        const options = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00'], // 示例时间数据
            axisLabel: {
              formatter: function(value) {
                return value
              },
              color: '#fff'
            },
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              formatter: '{value} °C',
              color: '#fff'
            },
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            splitLine: {
              lineStyle: {
                color: '#999'
              }
            }
          },
          series: [{
            name: '温度',
            type: 'line',
            smooth: true,
            lineStyle: {
              width: 3,
              shadowColor: 'rgba(0,0,0,0.3)',
              shadowBlur: 10,
              shadowOffsetY: 8
            },
            markLine: {
              silent: true,
              data: [{
                yAxis: 25
              }]
            },
            data: [22, 24, 21, 23, 25, 27] // 示例温度数据
          }]
        }
        // Apply options to the chart instance
        this.pieChartInstance.setOption(options)
      }
    },
    initializePieChart4() {
      // Check if the chart DOM element is available
      if (this.$refs.pieChart4) {
        // Initialize ECharts instance on the reference element
        this.pieChartInstance = echarts.init(this.$refs.pieChart4)

        // Define ECharts options
        const options = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['Download speed', 'Upload speed'],
            textStyle: {
              color: '#FFFFFF' // 设置图例文本的颜色为白色
            }
          },
          xAxis: {
            data: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00',
              '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00']
          },
          yAxis: {},
          series: [{
            name: 'Download speed',
            type: 'line',
            data: [5, 20, 36, 10, 10, 20] // 替换为你的下载速度数据
          },
          {
            name: 'Upload speed',
            type: 'line',
            data: [2, 10, 15, 8, 6, 12] // 替换为你的上传速度数据
          }]
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
              data: [{ value: this.cpuUsage }]
            }
          ] }
        this.pieChartInstance.setOption(options)
      }
    },
    handleInput() {
      const value = parseInt(this.id)
      if (isNaN(value)) {
        // 如果不是整数，则将输入的值设置为null
        this.id = null
      } else {
        // 如果是整数，则更新输入的值为整数
        this.id = value
      }
    },
    async fetchHardwareStatusById() {
      if (this.id === null) {
        this.$message({ message: '模块id不能为空', type: 'error', center: true })
        this.cpuUsage = 0
        this.memUsage = 0
        this.totalMem = 0
        this.lastMem = 0
        return
      }
      try {
        const { data } = await display1Api.getCpuUsageById(this.id) // 使用解构直接获取响应对象中的data属性
        if (data === -1 || data === null) {
          this.$message({ message: '请输入正确模块id或检查网络连接', type: 'warning', center: true })
          this.cpuUsage = 0
        } else {
          this.cpuUsage = 0
          this.$message({ message: '查询成功', type: 'success', center: true })
          this.cpuUsage = data
        } // 将data赋值给cpuUsage
      } catch (error) {
        console.error('Error fetching cpuUsage:', error)
      }
      try {
        const { data } = await display1Api.getMemUsageById(this.id)
        if (this.cpuUsage === 0) { this.memUsage = 0 } else this.memUsage = data
      } catch (error) {
        console.error('Error fetching memUsage:', error)
      }
      try {
        const { data } = await display1Api.getTotalMemById(this.id)
        if (this.cpuUsage === 0) { this.totalMem = 0 } else this.totalMem = data
      } catch (error) {
        console.error('Error fetching totalMem:', error)
      }
      try {
        const { data } = await display1Api.getLastMemById(this.id)
        if (this.cpuUsage === 0) { this.lastMem = 0 } else this.lastMem = data
      } catch (error) {
        console.error('Error fetching lastMem:', error)
      }
    }
  }
}
</script>
<style scoped>
#search2 .el-input {
  width: 100px;
  margin-right: 10px;
}
.el-dialog .el-input{
  width: 85%;
}
.el-button {
   width: 100px;
   margin-right: 10px;
 }
</style>

<style>
.text {
  font-size: 24px;
  font-family: Hiragino Sans GB,Helvetica Neue;
  font-palette: Blod;
}
.fill1{
  position: absolute;
  top: 0px;
  right: 0;
  bottom: 0;
  left: 0;
  background: url(bg.png);
}
.chart-containers{
  width: 600px;
  height: 400px;
}

.item {
  padding: 18px 0;
}
.layout-left-top2{
  left: 0;
  top: 150px;
  height: 50%;
  width: 50%;}
.layout-center-top2{
  top: 150px;
  left: 50%;
  right: 0;
  width: 50%;
  height: 50%;
}
.layout-right-top2{
  top: 50%;
  right: 0;
  height: 50%;
}
.layout-right-bottom2{
  top: 300px;
  left: 200px;
  width: 300px;
  right: 200px;
  bottom: 0;
  height: 300px;
}

</style>

<style scoped lang="scss">

</style>
