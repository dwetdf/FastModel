import request from '@/utils/request'

const signalApi = {
  FFTById(id, selectedFile) {
    const formData = new FormData()
    formData.append('id', id)
    formData.append('selectedFile', selectedFile)
    return request({
      url: '/test1/FFT',
      method: 'post',
      data: formData
    })
  },
  FIRById(id, selectedFile) {
    const formData = new FormData()
    formData.append('id', id)
    formData.append('selectedFile', selectedFile)
    return request({
      url: '/test1/FIR',
      method: 'post',
      data: formData
    })
  },
  FFT(value, selectedFile) {
    const formData = new FormData()
    formData.append('value', value)
    formData.append('selectedFile', selectedFile)
    return request({
      url: '/test1/FFTA',
      method: 'post',
      data: formData
    })
  },
  FIR(value, selectedFile) {
    const formData = new FormData()
    formData.append('value', value)
    formData.append('selectedFile', selectedFile)
    return request({
      url: '/test1/FIRA',
      method: 'post',
      data: formData
    })
  }
}
export default signalApi
