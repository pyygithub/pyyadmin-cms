import { createAPI } from '@/utils/request'

export const getPageById = (id) => createAPI(`/cms/page/${id}`, 'get')

export const getPageList = (page, size, params) => createAPI(`/cms/page/list/${page}/${size}`, 'get', params)

export const addPage = (page) => createAPI('/cms/page', 'post', page)

export const updatePage = (id, page) => createAPI(`/cms/page/${id}`, 'put', page)

export const deltetPage = (id) => createAPI(`/cms/page/${id}`, 'delete')


