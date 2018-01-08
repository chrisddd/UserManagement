package com.chris.usermanagement.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class of ValidationNumber
 *
 */
public class ValidationNumber {

	// 验证码图片的宽度
	private static final int IMAGE_WIDTH = 110;

	// 验证码图片的高度
	private static final int IMAGE_HEIGHT = 29;

	// 字体高度
	private static final int FONT_HEIGHT = IMAGE_HEIGHT - 4;

	private static final int CODE_Y = IMAGE_HEIGHT - 4;

	/***
	 * Validation code generation
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void createLogionValidationNumber(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.reset();

		int expectedResult = 0;

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 创建一个随机数生成器类
		Random random = new Random();

		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, FONT_HEIGHT);

		// 设置字体。
		g.setFont(font);

		// 画边框。
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, IMAGE_WIDTH - 1, IMAGE_HEIGHT - 1);

		// 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(Color.GREEN);
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(IMAGE_WIDTH);
			int y = random.nextInt(IMAGE_HEIGHT);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证
		StringBuilder randomCode = new StringBuilder();
		int a = random.nextInt(10);
		int b = random.nextInt(10);
		if (a < b) {
			a = a + b;
			b = a - b;
			a = a - b;
		}
		if (random.nextBoolean()) {
			expectedResult = a - b;
			randomCode.append(a).append("-").append(b);
		} else {
			expectedResult = a + b;
			randomCode.append(a).append("+").append(b);
		}

		char[] codes = randomCode.toString().toCharArray();
		int codeCount = codes.length;

		int x = IMAGE_WIDTH / (codeCount + 1);

		int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(red, green, blue));
			g.drawChars(codes, i, 1, (i + 1) * x, CODE_Y);
		}

		// 将四位数字的验证码保存到Session中。
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		session = request.getSession(true);
		session.setAttribute(Constants.VALI_CODE, expectedResult);

		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.flush();
		sos.close();
	}
}
