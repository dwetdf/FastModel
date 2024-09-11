import request from '@/utils/request'

const linktestApi = {
  Heartbeat() {
    return request({
      url: `/test4/heartbeat`,
      method: 'get'
    })
  },
  TaskComm() {
    return request({
      url: `/test4/taskcomm`,
      method: 'get'
    })
  },
  WebLink() {
    return request({
      url: `/test4/weblink`,
      method: 'get'
    })
  }
}
export default linktestApi
