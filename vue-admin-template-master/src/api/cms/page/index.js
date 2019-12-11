import { createAPI } from '@/utils/request'

export const getPageList = (page, size, params) => createAPI(`/cms/page/list/${page}/${size}`, 'get', params)


