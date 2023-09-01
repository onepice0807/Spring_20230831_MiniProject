package com.ray.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.util.FileCopyUtils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar;
import com.ray.vodto.UploadedFile;

/**
 * 
 * @MethodName : showWriteBoard
 * @author : ray
 * @param :
 * @throws IOException
 * @returnValue :
 * @description : 업로드 파일 처리
 * @date : 2023. 9. 1.
 */
public class uploadFileProcess {

	/**
	 * @MethodName : fileUpload
	 * @author : ray
	 * @param originalFilename
	 * @param size
	 * @param contentType
	 * @param bytes
	 * @param realPath(실제      저장되는 경로)
	 * @return
	 * @returnValue : realPath + date(realPath + 현재 년/ 월/ 일 폴더 경로)
	 * @description : 파일 업로드 처리의 전체 컨트롤
	 * @date : 2023. 9. 1.
	 */

	public static UploadedFile fileUpload(String originalFilename, long size, String contentType, byte[] data,
			String realPath) {

		String completePath = makeCalculaterPath(realPath); // 물리적 경로 + \년\월\일\

		UploadedFile uf = new UploadedFile();

		if (size > 0) {
			uf.setNewFileName(getNewFileName(originalFilename, realPath, completePath));

			uf.setOriginalFileName(originalFilename);
			uf.setSize(size);

			try {
				FileCopyUtils.copy(data, new File(realPath + uf.getNewFileName()));

				if (ImgMimeType.contentTypeIsImage(contentType)) {
					// 스케일 다운 -> thumnail 이름으로 파일저장
					makeThumNailImage(uf, realPath, completePath);

				}
			} catch (IOException e) {
				// 업로드된 원본파일 저장 실패
				e.printStackTrace();

				uf = null;
			}

		}

		if (uf != null) {
			System.out.println(uf.toString());
		}
		return uf;
	}

	/**
	 * @MethodName : makeThumNailImage
	 * @author : ray
	 * @param:
	 * @throws IOException
	 * @returnValue : 이미지(원본)을 읽어와 스케일 다운시키고, 썸내일 파일로 저장
	 * @description :
	 * @date : 2023. 9. 1.
	 */

	private static void makeThumNailImage(UploadedFile uf, String realPath, String completePath) throws IOException {

		BufferedImage originImg = ImageIO.read(new File(realPath + uf.getNewFileName()));

		BufferedImage thumNailImg = Scalr.resize(originImg, Mode.FIT_TO_HEIGHT, 50);

		String thumbImgName = "thumb" + uf.getOriginalFileName();

		String ext = uf.getOriginalFileName().substring(uf.getOriginalFileName().lastIndexOf(".") + 1);

		File saveTarget = new File(completePath + File.separator + thumbImgName);

		if (ImageIO.write(thumNailImg, ext, saveTarget)) {
			uf.setThumbFileName(completePath.substring(realPath.length()) + File.separator + thumbImgName);
		}
	}

	/**
	 * @MethodName : getNewFileName
	 * @author : ray
	 * @param originalFilename
	 * @param size
	 * @param contentType
	 * @param bytes
	 * @param realPath
	 * @returnValue : "\년\월\일\새로운 유니크한 파일이름.확장자 반환"
	 * @description :
	 * @date : 2023. 9. 1.
	 */

	private static String getNewFileName(String originalFilename, String realPath, String completePath) {
		String uuid = UUID.randomUUID().toString();

		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

		String newFileName = uuid + "_" + originalFilename;

		return completePath.substring(realPath.length()) + File.separator + newFileName;
	}

	private static String makeCalculaterPath(String realPath) {
		Calendar cal = Calendar.getInstance();

		String year = File.separator + (cal.get(Calendar.YEAR) + ""); // "\2023"

		String month = year + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1); // "\2023\09"
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // "\2023\09\01"

		System.out.println(year + "," + month + "," + date);

		makeDirectory(realPath, year, month, date);

		return realPath + date;
	}

	// ...strings : 가변인자 메서드 기법(전달된 year, month, date 값이 strings 하나의 매개변수로
	// 할당된다(배열형식으로)
	private static void makeDirectory(String realPath, String... strings) {
		// realPath 경로 + \년\월\일 폴더가 모드 존재하지 않는다면...
		if (!new File(realPath + strings[strings.length - 1]).exists()) {
			for (String path : strings) {
				File tmp = new File(realPath + path);
				if (!tmp.exists()) {
					tmp.mkdir();
				}
			}
		}

	}

}
