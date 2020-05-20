package com.zz.test.javafxmvn.maintabview.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.common.entity.PyProcessExample;
import com.zz.test.javafxmvn.common.entity.PyProcessExample.Criteria;
import com.zz.test.javafxmvn.common.entity.mapper.PyProcessMapper;
import com.zz.test.javafxmvn.commonbean.PageCommonRequest;
import com.zz.test.javafxmvn.commonbean.PageCommonResult;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.commoninterface.Function;
import com.zz.test.javafxmvn.commontag.TagBase;
import com.zz.test.javafxmvn.commontag.TagTool;
import com.zz.test.javafxmvn.commontool.pytool.StartMain;
import com.zz.test.javafxmvn.commontool.pytool.StartPyDemo;
import com.zz.test.javafxmvn.maintabview.view.LoginFxmlView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

@Service("startPyMainService")
public class StartPyMainService {

	@Autowired
	private CommonDb commonDb;
	
	@Autowired
	private PyProcessMapper pyProcessMapper;
	
	@Autowired
	private StartMain startMain;
	
	private LoginFxmlView loginFxmlView= new LoginFxmlView();
	
	public List<PyProcess> loginSerch(String type_code, String process_code) {
		// TODO Auto-generated method stub
		PyProcessExample pyProExa = new PyProcessExample();
		Criteria c = pyProExa.createCriteria();
		if(StringUtils.isNotBlank(type_code)) {
			c.andTypeCodeEqualTo(type_code);
		}
		if(StringUtils.isNotBlank(process_code)) {
			c.andProcessCodeEqualTo(process_code);
		}
		c.andDisableNotEqualTo(2);
		List<PyProcess> pyProList = pyProcessMapper.selectByExample(pyProExa);
		return pyProList;
	}

	public PageCommonResult loginSerchnew(PageCommonRequest request) {
		PyProcessExample pyProExa = new PyProcessExample();
		request.setMapper_example(pyProExa);
		Criteria c = pyProExa.createCriteria();
		if(StringUtils.isNotBlank((String) request.getValue("type_code"))) {
			c.andTypeCodeEqualTo((String) request.getValue("type_code"));
		}
		if(StringUtils.isNotBlank((String) request.getValue("process_code"))) {
			c.andProcessCodeEqualTo((String) request.getValue("process_code"));
		}
		c.andDisableNotEqualTo(2);
		
		return (PageCommonResult) commonDb.getListExample(pyProcessMapper.namespace + ".selectByExample", request);
				
	}
	
	public int updatePyProcessList(PyProcess py) {
		return pyProcessMapper.updateByPrimaryKey(py);
	}
	
	public int insertPyProcess(PyProcess py) {
		return pyProcessMapper.insertSelective(py);
	}


	public int deleteByPrimaryKeySet2(PyProcess p) {
		PyProcessExample pyProExa = new PyProcessExample();
		
		Criteria c = pyProExa.createCriteria();
		p.setDisable(2);
		return pyProcessMapper.updateByPrimaryKey(p);
	}
	
	public void startPyFile(String cmomand) {
		String pid = startMain.startPy(new String[] {"windows",cmomand});
		
	}

	
	public void startPyFileTest(String key) {
		if("1".equals(key)) {
			StartPyDemo.startByInterpreter(new String[]{"a =123123","print\\(a\\)"});
		}
		if("2".equals(key)) {
			StartPyDemo.startByPy("D:\\demo.py");
		}
		if("3".equals(key)) {
			StartPyDemo.startByRuntime("python  D:\\demo.py");
		}
		if("123".equals(key)) {
			StartPyDemo.startByInterpreter(new String[]{"a =123123","print\\(a\\)"});
			StartPyDemo.startByPy("D:\\demo.py");
			StartPyDemo.startByRuntime("python  D:\\demo.py");
		}
		
	}
	
	/**
	 * Desc:点击PaginationButi中的table的行触发事件。（如果PaginationButi中没有table，就不停冲击，到一定频次后结束）
	 * @author jld.zhangzhou
	 * @datetime 2020-05-14 18:20:26
	 * @modify_record:
	 * @param p
	 * @throws InterruptedException
	 */
	public void startPyMainTableClickRow(TagBase.PaginationButi p) throws InterruptedException {
		TagTool.TableTool.startPyMainTableClickRow(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getClickCount() == 1) {
					TableView<PyProcess> tab = TagTool.TableTool.tablePagePaginationGetTable(p);
					PyProcess py = tab.getSelectionModel().getSelectedItem();
					Button btn = (Button) p.getParent().getParent().lookup("#btn_edit");
					if(py != null && "编辑".equals(btn.getText())) {
						System.out.println("点击："+py.getProcessCode());
					}
				}
			}

			
		}, p);

	}
}
