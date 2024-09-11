import request from '@/utils/request'

const display1Api = {
  getCpuUsage() {
    return request({
      url: '/display1/CpuUsage',
      method: 'get'
    })
  },
  getMemUsage() {
    return request({
      url: '/display1/MemUsage',
      method: 'get'
    })
  },
  getTotalMem() {
    return request({
      url: '/display1/TotalMem',
      method: 'get'
    })
  },
  getLastMem() {
    return request({
      url: '/display1/LastMem',
      method: 'get'
    })
  },
  getModule() {
    return request({
      url: '/display3/module',
      method: 'get'
    })
  },
  getCpuUsageById(id) {
    return request({
      url: `/display4/CpuUsage/${id}`, // 对应后端的 @PathVariable
      method: 'get'
    })
  },
  getMemUsageById(id) {
    return request({
      url: `/display4/MemUsage/${id}`, // 对应后端的 @PathVariable
      method: 'get'
    })
  },
  getTotalMemById(id) {
    return request({
      url: `/display4/TotalMem/${id}`, // 对应后端的 @PathVariable
      method: 'get'
    })
  },
  getLastMemById(id) {
    return request({
      url: `/display4/LastMem/${id}`, // 对应后端的 @PathVariable
      method: 'get'
    })
  }
}

export default display1Api
