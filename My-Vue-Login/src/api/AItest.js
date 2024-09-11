import request from '@/utils/request'

const signalApi = {
  YoloById(id, selectedFile) {
    const formData = new FormData()
    formData.append('id', id)
    formData.append('videoFile', selectedFile)
    return request({
      url: '/test3/Yolo',
      method: 'post',
      data: formData
    })
  },
  SelectById(id, selectedFile1, selectedFile2) {
    const formData = new FormData()
    formData.append('id', id)
    formData.append('imageFile1', selectedFile1)
    formData.append('imageFile2', selectedFile2)
    return request({
      url: '/test3/Select',
      method: 'post',
      data: formData
    })
  },
  Yolo(value, selectedFile) {
    const formData = new FormData()
    formData.append('value', value)
    formData.append('videoFile', selectedFile)
    return request({
      url: '/test3/YoloA',
      method: 'post',
      data: formData
    })
  },
  Select(value, selectedFile1, selectedFile2) {
    const formData = new FormData()
    formData.append('value', value)
    formData.append('imageFile1', selectedFile1)
    formData.append('imageFile2', selectedFile2)
    return request({
      url: '/test3/SelectA',
      method: 'post',
      data: formData
    })
  }
}
export default signalApi
