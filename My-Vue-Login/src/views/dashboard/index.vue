<template>
  <div
    class="fill-parent"
    :dg-chart-options="{title:{show:false},legend:{top:0},grid:{top:25}}"
    :dg-chart-auto-resize="true"
    :dg-chart-theme="{color:'#F0F0F0',backgroundColor:'transparent',actualBackgroundColor:'#141c47'}"
  >
    <header class="head">
      <div class="title">
        <div class="title-left" />
        <div class="title-value">综合处理平台原型系统</div>
        <div class="title-right" />
      </div>
    </header>
    <main class="content">
      <section class="layout layout-left-top">
        <panel-component title="模块监控">
          <div ref="pieChart" class="chart" :dg-chart-auto-resize="true" />
        </panel-component>
      </section>
      <section class="layout layout-left-bottom">
        <panel-component title="各模块连接测试">
          <div class="chart" dg-chart-widget="" />
          <div class="table-container">
            <table-component :data="connectionTestData" />
          </div>
        </panel-component>
      </section>
      <section class="layout layout-center-top">
        <panel-component title="资源运行侦测">
          <div ref="lineChart" class="chart" dg-chart-widget="" />
        </panel-component>
      </section>
      <section class="layout layout-right-top">
        <panel-component title="各模块供电侦测">
          <div class="chart" dg-chart-widget="" />
          <div class="table-container">
            <table-component :data="powerSupplyData" />
          </div>
        </panel-component>
      </section>
      <section class="layout layout-right-bottom">
        <panel-component title="测试算法库">
          <div class="chart" dg-chart-widget="">
            <div class="table-container">
              <table-component :data="algorithmData" />
            </div>
          </div>
        </panel-component>
      </section>
    </main>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import display1Api from '@/api/resourceDisplay'

// Panel Component
const PanelComponent = {
  props: ['title'],
  render(h) {
    return h('div', { class: 'panel border-all title-bg' }, [
      h('div', { class: 'title' }, this.title),
      this.$slots.default,
      h('div', { class: 'border-foot' })
    ])
  }
}

// Table Component
const TableComponent = {
  props: ['data'],
  render(h) {
    return h('table', { class: 'custom-table' }, [
      // 表头
      h('tr', this.data.headers.map(header =>
        h('th', { key: header }, header)
      )),
      // 表格内容
      ...this.data.rows.map(row =>
        h('tr', { key: row.id }, row.values.map((value, index) =>
          h('td', { key: index }, value)
        ))
      )
    ])
  }
}
export default {
  components: {
    PanelComponent,
    TableComponent
  },
  data() {
    return {
      cpuUsage: null,
      memUsage: null,
      totalMem: null,
      lastMem: null,
      lineChartInstance: null,
      pieChartInstance: null,
      connectionTestData: {
        headers: ['模块ID', 'Agent心跳测试', '任务通信测试', '网络连接测试'],
        rows: [
          { id: 1, values: ['1', '心跳保持', '通信正常', '网络连接'] },
          { id: 2, values: ['2', '心跳保持', '通信正常', '网络连接'] },
          { id: 3, values: ['3', '心跳异常', '通信正常', '网络异常'] },
          { id: 4, values: ['4', '心跳保持', '通信异常', '网络异常'] },
          { id: 5, values: ['5', '心跳保持', '通信正常', '网络连接'] },
          { id: 6, values: ['6', '心跳保持', '通信正常', '网络异常'] },
          { id: 7, values: ['7', '心跳保持', '通信异常', '网络连接'] }
        ]
      },
      powerSupplyData: {
        headers: ['模块ID', '供电情况'],
        rows: [
          { id: 1, values: ['1', '供电正常'] },
          { id: 2, values: ['2', '供电正常'] },
          { id: 3, values: ['3', '供电异常'] },
          { id: 4, values: ['4', '供电正常'] },
          { id: 5, values: ['5', '供电异常'] },
          { id: 6, values: ['6', '供电正常'] },
          { id: 7, values: ['7', '供电正常'] }
        ]
      },
      algorithmData: {
        headers: ['算法ID', '算法名称'],
        rows: [
          { id: 1, values: ['1', 'FIR'] },
          { id: 2, values: ['2', 'FFT'] },
          { id: 3, values: ['3', 'Gauss'] },
          { id: 4, values: ['4', 'Select'] },
          { id: 5, values: ['5', 'YOLO'] },
          { id: 6, values: ['6', 'BYJC'] }
        ]
      }
    }
  },
  mounted() {
    this.fetchHardwareStatus().then(() => {
      if (this.$refs.lineChart) {
        this.initializeLineChart()
      }
      if (this.$refs.pieChart) {
        this.initializePieChart()
      }
    })
  },
  methods: {
    initializeLineChart() {
      this.lineChartInstance = echarts.init(this.$refs.lineChart)
      const options = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['内存使用率', 'CPU占用率'],
          textStyle: {
            color: '#fff'
          }
        },
        xAxis: [{
          type: 'category',
          data: ['模块一', '模块二', '模块三', '模块四', '模块五', '模块六', '模块七'],
          axisPointer: {
            type: 'shadow'
          },
          axisLabel: {
            margin: 10,
            textStyle: {
              color: '#fff'
            }
          }
        }],
        yAxis: [
          {
            type: 'value',
            name: '内存使用率',
            min: 0,
            max: 100,
            interval: 25,
            axisLabel: {
              textStyle: {
                color: '#fff'
              },
              formatter: '{value} %'
            }
          },
          {
            type: 'value',
            name: 'CPU占用率',
            min: 0,
            max: 100,
            interval: 25,
            axisLabel: {
              textStyle: {
                color: '#fff'
              },
              formatter: '{value} %'
            }
          }
        ],
        series: [
          {
            name: '内存使用率',
            type: 'bar',
            tooltip: {
              valueFormatter: function(value) {
                return value + ' %'
              }
            },
            data: [68.66, 65.99, 81.22, 72.58, 32.22, 91.52, 21.02]
          },
          {
            name: 'CPU占用率',
            type: 'bar',
            tooltip: {
              valueFormatter: function(value) {
                return value + ' %'
              }
            },
            data: [70, 65, 80, 75, 52, 90, 25]
          }
        ]
      }
      this.lineChartInstance.setOption(options)
    },
    initializePieChart() {
      this.pieChartInstance = echarts.init(this.$refs.pieChart)
      const options = {
        tooltip: {
          trigger: 'item'
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
              { value: 4, name: '正在使用模块' },
              { value: 1, name: '未使用模块' },
              { value: 2, name: '故障模块' }
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
                color: '#FFFFFF'
              }
            }
          }
        ]
      }
      this.pieChartInstance.setOption(options)
    },
    async fetchHardwareStatus() {
      this.$message({ message: '已进行获取', type: 'success', center: true })
      try {
        const { data: cpuUsage } = await display1Api.getCpuUsage()
        const { data: memUsage } = await display1Api.getMemUsage()
        const { data: totalMem } = await display1Api.getTotalMem()
        const { data: lastMem } = await display1Api.getLastMem()

        this.cpuUsage = cpuUsage
        this.memUsage = memUsage
        this.totalMem = totalMem
        this.lastMem = lastMem
      } catch (error) {
        console.error('Error fetching hardware status:', error)
      }
    }
  }
}
</script>
<style scoped lang="scss">

</style>
<style>

.fill-parent{
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: url(bg.png);
}
.head{
  height: 4rem;
  background: url(title.png);
  background-size: 100% 100%;
}

.head .title{
  font-size: 1.8rem;
  text-align: center;
  margin-top: 0.5rem;
  color: #ffffff;
  height: 3rem;
  line-height: 3rem;
  position: relative;
}
.head .title .title-left{
  position: absolute;
  top: 50%;
  left: 0;
  width: 35%;
  height: 3px;
  margin-top: -1.5px;
  background: #0089ff;
}
.head .title .title-value{
  position: absolute;
  color: #ffffff;
  top: 0;
  left: 35%;
  right: 35%;
  bottom: 0;
  border-right: 3px solid #0089ff;
  border-left: 3px solid #0089ff;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
}
.head .title .title-right{
  position: absolute;
  top: 50%;
  right: 0;
  width: 35%;
  height: 3px;
  margin-top: -1.5px;
  background: #0089ff;
}
.content{
  position: absolute;
  top: 4.5rem;
  right: 0;
  bottom: 0;
  left: 0;
}
.layout{
  position: absolute;
  width: 25%;
}
.layout-left-top{
  left: 0;
  top: 0;
  height: 66.66%;
}
.layout-left-bottom{
  left: 0;
  top: 66.66%;
  height: 33.33%;
  width: 75%;
  bottom: 0;
}
.table-container {
  position: absolute;
  top: 1.5rem;
  width: 100%;
  height: 80%; /* 设置容器的高度 */
  overflow-y: auto; /* 添加垂直滚动条 */
}
.custom-table {
  width: 100%; /* 表格宽度填满容器 */
}

.custom-table th, .custom-table td {
  border: 1px solid #ffffff; /* 设置单元格边框为1px实线黑色 */
  padding: 8px; /* 设置单元格内边距 */
  text-align: center; /* 文本居中 */
  color: #fff; /* 设置字体颜色为白色 */
}

.custom-table th {
  background-color: #05137c; /* 设置表头背景色 */
}
.table-container::-webkit-scrollbar {
  width: 8px; /* 设置滚动条宽度 */
}

.table-container::-webkit-scrollbar-thumb {
  background-color: #888; /* 设置滚动条颜色 */
  border-radius: 4px; /* 设置滚动条圆角 */
}
.layout-center-top{
  top: 0;
  left: 25%;
  right: 25%;
  width: 50%;
  height: 66.6%;
}

.layout-right-top{
  top: 0;
  right: 0;
  height: 50%;
}
.layout-right-bottom{
  top: 50%;
  right: 0;
  bottom: 0;
}
.panel{
  position: absolute;
  left: 0.5rem;
  top: 0.8rem;
  right: 0.5rem;
  bottom: 0.8rem;
  border: 1px solid #20558b;
  -webkit-box-shadow: #07417a 0 0 10px;
  -moz-box-shadow: #07417a 0 0 10px;
  box-shadow: inset 0 0 30px #07417a;
}
.panel.border-left-top:before,
.panel.border-all:before{
  position: absolute;
  width: 1rem;
  height: 1rem;
  content: "";
  border-top: 3px solid #488aff;
  border-left: 3px solid #488aff;
  left: -1px;
  top: -1px;
}
.panel.border-right-top:after,
.panel.border-all:after{
  position: absolute;
  width: 1rem;
  height: 1rem;
  content: "";
  border-top: 3px solid #488aff;
  border-right: 3px solid #488aff;
  right: -1px;
  top: -1px;
}
.panel.border-left-bottom .border-foot:before,
.panel.border-all .border-foot:before{
  position: absolute;
  width: 1rem;
  height: 1rem;
  content: "";
  border-bottom: 3px solid #488aff;
  border-left: 3px solid #488aff;
  left: -1px;
  bottom: -1px;
}
.panel.border-right-bottom .border-foot:after,
.panel.border-all .border-foot:after{
  position: absolute;
  width: 1rem;
  height: 1rem;
  content: "";
  border-bottom: 3px solid #488aff;
  border-right: 3px solid #488aff;
  right: -1px;
  bottom: -1px;
}
.panel > .title{
  position: absolute;
  top: 0.2rem;
  right: 0.5rem;
  left: 0.5rem;
  line-height: 2rem;
  font-size: 1.1rem;
  font-weight: bold;
}
.panel.title-bg > .title{
  position: absolute;
  top: -0.8rem;
  right: 20%;
  left: 20%;
  text-align: center;
  color: #fff;
  /*background: url(title-bg.png);*/
  background-size: 100% 100%;
}

.panel .chart{
  position: absolute;
  top: 2rem;
  right: 0.3rem;
  bottom: 0.3rem;
  left: 0.3rem;
}
.panel.title-bg .chart{
  top: 1.4rem;
}
.layout-center-bottom .panel{
  background-image: -webkit-radial-gradient(250px 250px at 50% 50%,hsla(209,99%,32%,28),hsla(228,100%,0%,0));
  background-image: radial-gradient(250px 250px at 50% 50%,hsla(209,99%,32%,28),hsla(228,100%,0%,0));
}

</style>
