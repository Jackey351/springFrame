package com.yanfei.springFrame.Utils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.yanfei.springFrame.Pojo.BaseQueryCondition;

public class RequestUtil {
	public static boolean checkText(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(text);
		if (!m.matches()) {
			return false;
		}
		return true;
	}
	
	public static <T> Map<String, Object> dividePage(BaseQueryCondition queryCondition, List<T> queryResult) {
		int total = queryResult.size();
		if (total == 0) {
			Map<String, Object> resultMap = ResultUtil.toMap("form", queryResult);
			resultMap.put("total", total);
			return resultMap;
		}
		if (queryCondition.getPageIndex() == 0 && queryCondition.getPageSize() == -1) {
			queryCondition.setPageSize(queryResult.size());
		}
		int fromIndex = queryCondition.getPageIndex() * queryCondition.getPageSize();
		int toIndex = fromIndex + queryCondition.getPageSize();
		if (fromIndex > queryResult.size()) {
			throw new IllegalStateException("分页参数错误。");
		}
		if (toIndex >= queryResult.size()) {
			toIndex = queryResult.size();
		}
		Map<String, Object> resultMap = ResultUtil.toMap("form", queryResult.subList(fromIndex, toIndex));
		resultMap.put("total", total);
		return resultMap;
	}
	
	/**
	 * 根据List获取到对应的JSONArray
	 * @param list
	 * @return
	 */
	public static JSONArray getJSONArrayByList(List<?> list) {
		JSONArray jsonArray = new JSONArray();
		if (list == null || list.isEmpty()) {
			return jsonArray;// nerver return null
		}
		
		for (Object object : list) {
			jsonArray.add(object);
		}
		return jsonArray;
	}
}
