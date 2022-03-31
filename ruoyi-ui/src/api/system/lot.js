import request from '@/utils/request'

// 查询车场管理列表
export function listLot(query) {
  return request({
    url: '/system/lot/list',
    method: 'get',
    params: query
  })
}

// 查询车场管理详细
export function getLot(lotId) {
  return request({
    url: '/system/lot/' + lotId,
    method: 'get'
  })
}

// 新增车场管理
export function addLot(data) {
  return request({
    url: '/system/lot',
    method: 'post',
    data: data
  })
}

// 修改车场管理
export function updateLot(data) {
  return request({
    url: '/system/lot',
    method: 'put',
    data: data
  })
}

// 删除车场管理
export function delLot(lotId) {
  return request({
    url: '/system/lot/' + lotId,
    method: 'delete'
  })
}
