package com.hanbit.team05.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.team05.core.dao.AdminDAO;
import com.hanbit.team05.core.session.Session;
import com.hanbit.team05.core.session.SessionHelper;
import com.hanbit.team05.core.vo.ConsultVO;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;

	public Map getConsult(int pageNum, String flag) {

		Session session = SessionHelper.getSession();

		Map data = new HashMap<>();
		data.put("pageNum", pageNum);
		data.put("flag", flag);

		List<ConsultVO> consultVO = adminDAO.getConsult(data);
		int totalCount = adminDAO.totalCount(flag);

		Map result = new HashMap();
		result.put("totalCount", totalCount);
		result.put("consultVO", consultVO);

		return result;
	}
}
