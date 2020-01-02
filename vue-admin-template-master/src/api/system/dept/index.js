import { createAPI } from '@/utils/request'

/** 服务器API接口网关路径 */
const api_gateway_url = '/v1/base-server';

export const getDeptById = (id) => createAPI(`${api_gateway_url}/dept/${id}`, 'get')

export const getDeptTreeList = (params) => createAPI(`${api_gateway_url}/dept/treeList`, 'get', params)

export const addDept = (dept) => createAPI(`${api_gateway_url}/dept`, 'post', dept)

export const updateDept = (id, dept) => createAPI(`${api_gateway_url}/dept/${id}`, 'put', dept)

export const deleteDept = (id) => createAPI(`${api_gateway_url}/dept/${id}`, 'delete')



