package com.yanfei.springFrame.Config;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.yanfei.springFrame.Utils.ResultUtil;

//自定义检验响应体
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResultUtil handlerException(Exception e) {
		e.printStackTrace();
		return ResultUtil.fail(e.getMessage() == null ? "服务器错误" : e.getMessage());
	}
	
	/**
	* 处理实体字段校验不通过异常
	* @param ex
	* @return
	*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResultUtil validationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuilder builder = new StringBuilder();
		
		for (FieldError error : fieldErrors) {
			builder.append(error.getDefaultMessage() + "\n");
		}
		return ResultUtil.fail(builder.toString());
	}
	
	/**
	* 处理查不到数据异常
	* @param ex
	* @return
	*/
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseBody
	public ResultUtil validationError(EmptyResultDataAccessException ex) {
		return ResultUtil.fail("数据不存在");
	}
	
	/**
	* 处理实体字段校验不通过异常
	* @param ex
	* @return
	*/
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	public ResultUtil validationError(EntityNotFoundException ex) {
		return ResultUtil.fail(ex.getMessage());
	}
	
	/**
	* 处理实体字段为空异常
	* @param ex
	* @return
	*/
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	@ResponseBody
	public ResultUtil validationError(InvalidDataAccessApiUsageException ex) {
		return ResultUtil.fail(ex.getMessage());
	}
	
	/**
	* 处理数据类型异常
	* @param ex
	* @return
	*/
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResultUtil validationError(MethodArgumentTypeMismatchException ex) {
		return ResultUtil.fail("数据类型输入错误");
	}
	
	/**
	* 处理重返插入数据库异常，此处针对user的openId
	* @param ex
	* @return
	*/
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseBody
	public ResultUtil validationError(SQLIntegrityConstraintViolationException ex) {
		return ResultUtil.ok("用户已存在");
	}
	
}
