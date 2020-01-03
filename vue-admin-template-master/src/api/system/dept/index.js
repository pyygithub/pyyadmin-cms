import { BASE_SERVER_API_GATEWAY_URL } from '@/api/serverUrl'
import { createAPI } from '@/utils/request'


export const getDeptById = (id) => createAPI(`${BASE_SERVER_API_GATEWAY_URL}/dept/${id}`, 'get')

export const getDeptTreeList = (params) => createAPI(`${BASE_SERVER_API_GATEWAY_URL}/dept/treeList`, 'get', params)

export const addDept = (dept) => createAPI(`${BASE_SERVER_API_GATEWAY_URL}/dept`, 'post', dept)

export const updateDept = (id, dept) => createAPI(`${BASE_SERVER_API_GATEWAY_URL}/dept/${id}`, 'put', dept)

export const deleteDept = (id) => createAPI(`${BASE_SERVER_API_GATEWAY_URL}/dept/${id}`, 'delete')



