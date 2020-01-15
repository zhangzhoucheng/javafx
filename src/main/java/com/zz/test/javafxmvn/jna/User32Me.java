package com.zz.test.javafxmvn.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 
 * <note>
 * Desc： User32接口
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-14 10:32:52
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-14 10:32:52    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public interface User32Me extends StdCallLibrary{
	User32Me INSTANCE = (User32Me) Native.loadLibrary("user32", User32Me.class);
	int GWL_WNDPROC = -4;
	boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);

	int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);

	int SetWindowLong(HWND hwnd, int gwlExstyle, int i);
}
