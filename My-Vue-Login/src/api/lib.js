import request from '@/utils/request'

export default {
  getsignalList(searchModel) {
    return request({
      url: '/lib/signal',
      method: 'get',
      params: { // 传给后端的参数，对应后端的 @RequestParam
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        shortname: searchModel.shortname,
        user: searchModel.user
      }
    })
  },
  getpicList(searchModel) {
    return request({
      url: '/lib/pic',
      method: 'get',
      params: { // 传给后端的参数，对应后端的 @RequestParam
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        shortname: searchModel.shortname,
        user: searchModel.user
      }
    })
  },
  getaiList(searchModel) {
    return request({
      url: '/lib/ai',
      method: 'get',
      params: { // 传给后端的参数，对应后端的 @RequestParam
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        shortname: searchModel.shortname,
        user: searchModel.user
      }
    })
  }
}

