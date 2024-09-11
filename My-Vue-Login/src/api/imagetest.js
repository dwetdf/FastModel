import request from '@/utils/request'

const signalApi = {
  GaussById(id, selectedFile) {
    const formData = new FormData()
    formData.append('id', id)
    formData.append('imageFile', selectedFile)
    return request({
      url: '/test2/Gauss',
      method: 'post',
      data: formData
    })
  },
  BYJCById(id, selectedFile) {
    const formData = new FormData()
    formData.append('id', id)
    formData.append('imageFile', selectedFile)
    return request({
      url: '/test2/BYJC',
      method: 'post',
      data: formData
    })
  },
  Gauss(value, selectedFile) {
    const formData = new FormData()
    formData.append('value', value)
    formData.append('imageFile', selectedFile)
    return request({
      url: '/test2/GaussA',
      method: 'post',
      data: formData
    })
  },
  BYJC(value, selectedFile) {
    const formData = new FormData()
    formData.append('value', value)
    formData.append('imageFile', selectedFile)
    return request({
      url: '/test2/BYJCA',
      method: 'post',
      data: formData
    })
  }
}
export default signalApi
