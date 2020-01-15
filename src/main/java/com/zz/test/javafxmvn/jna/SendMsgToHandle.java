package com.zz.test.javafxmvn.jna;


import org.springframework.util.StringUtils;

import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;

public class SendMsgToHandle {
	
	/**
	 * Desc:发送消息到句柄窗体
	 * @author jld.zhangzhou
	 * @datetime 2020-01-14 16:25:51
	 * @modify_record:
	 * @param handtitle 句柄标题
	 * @param content 发送内容
	 */
	public static void sendMsgByTitle(String handtitle,String content) {
		//@TODO  1、处理获取tim多个窗口的title；2、发送中文等信息问题；3：获取窗口信息。
		// 第一个参数是Windows窗体的窗体类，第二个参数是窗体的标题。
		HWND hwnd = User32.INSTANCE.FindWindow(null, handtitle);
		if (hwnd == null) {
			System.out.println(handtitle + " is not running");
			return;
		}
		
		if(StringUtils.isEmpty(content)) {
			System.out.println(content + " is null");
			return;
		}
		content += "\r";
		User32.INSTANCE.ShowWindow(hwnd, 9); // SW_RESTORE
		User32.INSTANCE.SetForegroundWindow(hwnd); // bring to front
		WinDef.RECT qqwin_rect = new WinDef.RECT();
		User32.INSTANCE.GetWindowRect(hwnd, qqwin_rect);
		int qqwin_width = qqwin_rect.right - qqwin_rect.left;
		int qqwin_height = qqwin_rect.bottom - qqwin_rect.top;
		User32.INSTANCE.MoveWindow(hwnd, 0, 0, qqwin_width, qqwin_height, true);
		User32.INSTANCE.PostMessage(hwnd, User32.KEY_SET_VALUE, null, null);
		for (Character c : content.toCharArray())
			sendChar(c);
	
	}
	
	private static WinUser.INPUT input = new WinUser.INPUT();
	private static void sendChar(char ch) {
		input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
		input.input.setType("ki");
		input.input.ki.wScan = new WinDef.WORD(0);
		input.input.ki.time = new WinDef.DWORD(0);
		input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
		// Press
		input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
		input.input.ki.dwFlags = new WinDef.DWORD(0); // keydown
		User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
		// Release
		input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
		input.input.ki.dwFlags = new WinDef.DWORD(2); // keyup
		User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
	}
}
