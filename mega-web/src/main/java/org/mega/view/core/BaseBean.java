package org.mega.view.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.mega.common.Lib;
import org.mega.constant.Constant;
import org.mega.dao.entity.Customer;
import org.mega.dao.entity.Menu;
import org.mega.dao.entity.Page;
import org.mega.dto.core.Dto;
import org.mega.dto.core.PageDto;
import org.mega.dto.core.TableData;
import org.mega.dto.core.TableDto;
import org.mega.view.control.PopupBean;

public abstract class BaseBean extends CoreBean {
	public static final String FROM_BUSINESS_CODE = "fromBusinessCode";
	public static final String FROM_BUSINESS_ID = "fromBusinessId";
	public static final String BUSINESS_ADD_MORE = "businessAddMore";
	public static final String REPORT_DATA = "reportData";
	public static final String REPORT_FILE_NAME = "reportFileName";
	public static final String REPORT_CONTENT_TYPE = "reportContentType";
	public static final String REPORT_ENCODING = "reportEncoding";

	public static final String POPUP_PAGE = "popupPage";

	public static final String LOGIN = "login";

	public static final String ACC00 = "acc00";
	public static final String ACC01 = "acc01";
	public static final String ACC02 = "acc02";
	public static final String ACC03 = "acc03";
	public static final String ACC04 = "acc04";
	public static final String ACC05 = "acc05";
	public static final String ACC06 = "acc06";
	public static final String ACC11 = "acc11";
	public static final String ACC12 = "acc12";
	public static final String ACC13 = "acc13";
	public static final String ACC14 = "acc14";
	public static final String ACC15 = "acc15";
	public static final String ACC16 = "acc16";
	public static final String ACC21 = "acc21";
	public static final String ACC22 = "acc22";
	public static final String ACC23 = "acc23";
	public static final String ACC24 = "acc24";
	public static final String ACC25 = "acc25";
	public static final String ACC31 = "acc31";
	public static final String ACC32 = "acc32";
	public static final String ACC33 = "acc33";
	public static final String ACC41 = "acc41";
	public static final String ACC42 = "acc42";
	public static final String ACC51 = "acc51";
	public static final String ACC52 = "acc52";
	public static final String ACC53 = "acc53";
	public static final String ADM00 = "adm00";
	public static final String ADM01 = "adm01";
	public static final String ADM13 = "adm13";
	public static final String ADM31 = "adm31";
	public static final String ADM32 = "adm32";
	public static final String ADM41 = "adm41";
	public static final String ADM42 = "adm42";
	public static final String ADM43 = "adm43";

	public static final String CMN01 = "cmn01";
	public static final String CMN11 = "cmn11";
	public static final String CMN12 = "cmn12";
	public static final String CMN13 = "cmn13";
	public static final String CMN14 = "cmn14";
	public static final String CMN15 = "cmn15";
	public static final String CMN31 = "cmn31";
	public static final String CMN32 = "cmn32";
	public static final String CMN33 = "cmn33";
	public static final String CMN34 = "cmn34";
	public static final String CMN35 = "cmn35";
	public static final String CMN36 = "cmn36";
	public static final String HRM00 = "hrm00";
	public static final String HRM01 = "hrm01";
	public static final String HRM02 = "hrm02";
	public static final String HRM03 = "hrm03";
	public static final String HRM04 = "hrm04";
	public static final String HRM11 = "hrm11";
	public static final String HRM21 = "hrm21";
	public static final String HRM42 = "hrm42";
	public static final String HRM51 = "hrm51";
	public static final String HRM52 = "hrm52";
	public static final String HRM61 = "hrm61";
	public static final String HRM62 = "hrm62";
	public static final String INV01 = "inv01";
	public static final String INV02 = "inv02";
	public static final String INV03 = "inv03";
	public static final String INV04 = "inv04";
	public static final String INV05 = "inv05";
	public static final String INV06 = "inv06";
	public static final String INV11 = "inv11";
	public static final String INV12 = "inv12";
	public static final String INV13 = "inv13";
	public static final String INV21 = "inv21";
	public static final String INV22 = "inv22";
	public static final String INV23 = "inv23";
	public static final String OFC01 = "ofc01";
	public static final String OFC11 = "ofc11";
	public static final String PUR00 = "pur00";
	public static final String PUR01 = "pur01";
	public static final String PUR02 = "pur02";
	public static final String PUR03 = "pur03";
	public static final String PUR04 = "pur04";
	public static final String PUR11 = "pur11";
	public static final String PUR12 = "pur12";
	public static final String PUR13 = "pur13";
	public static final String PUR21 = "pur21";
	public static final String PUR22 = "pur22";
	public static final String PUR31 = "pur31";
	public static final String SLE01 = "sle01";
	public static final String SLE02 = "sle02";
	public static final String SLE03 = "sle03";
	public static final String SLE04 = "sle04";
	public static final String SLE11 = "sle11";
	public static final String SLE12 = "sle12";
	public static final String SLE13 = "sle13";
	public static final String SLE14 = "sle14";
	public static final String SLE15 = "sle15";
	public static final String SLE16 = "sle16";
	public static final String SLE21 = "sle21";
	public static final String SVC01 = "svc01";
	public static final String SVC11 = "svc11";
	public static final String SVC12 = "svc12";
	public static final String SVC13 = "svc13";
	public static final String SVC14 = "svc14";
	public static final String SVC15 = "svc15";
	public static final String SVC16 = "svc16";
	public static final String SVC17 = "svc17";
	public static final String SVC21 = "svc21";
	public static final String SVC02 = "svc02";
	public static final String SVC03 = "svc03";
	public static final String SVC04 = "svc04";
	public static final String TPL01 = "tpl01";
	public static final String TPL11 = "tpl11";
	public static final String TPL21 = "tpl21";
	public static final String TPL31 = "tpl31";
	public static final String TPL41 = "tpl41";
	public static final String TPL51 = "tpl51";
	public static final String WRK01 = "wrk01";
	public static final String WRK02 = "wrk02";
	public static final String WRK03 = "wrk03";
	public static final String WRK04 = "wrk04";
	public static final String WRK05 = "wrk05";
	public static final String WRK21 = "wrk21";
	public static final String WRK22 = "wrk22";
	public static final String WRK23 = "wrk23";
	public static final String WRK24 = "wrk24";
	public static final String HRM41 = "hrm41";
	public static final String MSG01 = "msg01";

	public static final String ACC000 = "acc000";
	public static final String ACC010 = "acc010";
	public static final String ACC011 = "acc011";
	public static final String ACC012 = "acc012";
	public static final String ACC013 = "acc013";
	public static final String ACC020 = "acc020";
	public static final String ACC021 = "acc021";
	public static final String ACC022 = "acc022";
	public static final String ACC030 = "acc030";
	public static final String ACC031 = "acc031";
	public static final String ACC032 = "acc032";
	public static final String ACC040 = "acc040";
	public static final String ACC041 = "acc041";
	public static final String ACC042 = "acc042";
	public static final String ACC050 = "acc050";
	public static final String ACC051 = "acc051";
	public static final String ACC052 = "acc052";
	public static final String ACC060 = "acc060";
	public static final String ACC061 = "acc061";
	public static final String ACC062 = "acc062";
	public static final String ACC110 = "acc110";
	public static final String ACC111 = "acc111";
	public static final String ACC112 = "acc112";
	public static final String ACC120 = "acc120";
	public static final String ACC121 = "acc121";
	public static final String ACC122 = "acc122";
	public static final String ACC130 = "acc130";
	public static final String ACC131 = "acc131";
	public static final String ACC132 = "acc132";
	public static final String ACC140 = "acc140";
	public static final String ACC141 = "acc141";
	public static final String ACC142 = "acc142";
	public static final String ACC150 = "acc150";
	public static final String ACC151 = "acc151";
	public static final String ACC152 = "acc152";
	public static final String ACC160 = "acc160";
	public static final String ACC161 = "acc161";
	public static final String ACC162 = "acc162";
	public static final String ACC210 = "acc210";
	public static final String ACC211 = "acc211";
	public static final String ACC212 = "acc212";
	public static final String ACC220 = "acc220";
	public static final String ACC221 = "acc221";
	public static final String ACC222 = "acc222";
	public static final String ACC230 = "acc230";
	public static final String ACC231 = "acc231";
	public static final String ACC232 = "acc232";
	public static final String ACC240 = "acc240";
	public static final String ACC241 = "acc241";
	public static final String ACC242 = "acc242";
	public static final String ACC250 = "acc250";
	public static final String ACC251 = "acc251";
	public static final String ACC252 = "acc252";
	public static final String ACC310 = "acc310";
	public static final String ACC311 = "acc311";
	public static final String ACC312 = "acc312";
	public static final String ACC313 = "acc313";
	public static final String ACC320 = "acc320";
	public static final String ACC321 = "acc321";
	public static final String ACC322 = "acc322";
	public static final String ACC330 = "acc330";
	public static final String ACC331 = "acc331";
	public static final String ACC332 = "acc332";
	public static final String ACC410 = "acc410";
	public static final String ACC411 = "acc411";
	public static final String ACC412 = "acc412";
	public static final String ACC420 = "acc420";
	public static final String ACC421 = "acc421";
	public static final String ACC422 = "acc422";
	public static final String ACC510 = "acc510";
	public static final String ACC511 = "acc511";
	public static final String ACC512 = "acc512";
	public static final String ACC520 = "acc520";
	public static final String ACC530 = "acc530";

	public static final String ADM010 = "adm010";
	public static final String ADM011 = "adm011";
	public static final String ADM012 = "adm012";
	public static final String ADM130 = "adm130";
	public static final String ADM131 = "adm131";
	public static final String ADM132 = "adm132";
	public static final String ADM310 = "adm310";
	public static final String ADM320 = "adm320";
	public static final String ADM410 = "adm410";
	public static final String ADM421 = "adm421";
	public static final String ADM422 = "adm422";
	public static final String ADM431 = "adm431";
	public static final String ADM432 = "adm432";

	public static final String CMN000 = "cmn000";
	public static final String CMN010 = "cmn010";
	public static final String CMN011 = "cmn011";
	public static final String CMN012 = "cmn012";
	public static final String CMN110 = "cmn110";
	public static final String CMN111 = "cmn111";
	public static final String CMN112 = "cmn112";
	public static final String CMN120 = "cmn120";
	public static final String CMN121 = "cmn121";
	public static final String CMN122 = "cmn122";
	public static final String CMN130 = "cmn130";
	public static final String CMN131 = "cmn131";
	public static final String CMN132 = "cmn132";
	public static final String CMN140 = "cmn140";
	public static final String CMN141 = "cmn141";
	public static final String CMN142 = "cmn142";
	public static final String CMN150 = "cmn150";
	public static final String CMN151 = "cmn151";
	public static final String CMN152 = "cmn152";
	public static final String CMN310 = "cmn310";
	public static final String CMN311 = "cmn311";
	public static final String CMN312 = "cmn312";
	public static final String CMN320 = "cmn320";
	public static final String CMN321 = "cmn321";
	public static final String CMN322 = "cmn322";
	public static final String CMN330 = "cmn330";
	public static final String CMN331 = "cmn331";
	public static final String CMN332 = "cmn332";
	public static final String CMN340 = "cmn340";
	public static final String CMN341 = "cmn341";
	public static final String CMN342 = "cmn342";
	public static final String CMN350 = "cmn350";
	public static final String CMN351 = "cmn351";
	public static final String CMN352 = "cmn352";
	public static final String CMN360 = "cmn360";
	public static final String CMN361 = "cmn361";
	public static final String CMN362 = "cmn362";

	public static final String HRM000 = "hrm000";
	public static final String HRM010 = "hrm010";
	public static final String HRM011 = "hrm011";
	public static final String HRM012 = "hrm012";
	public static final String HRM020 = "hrm020";
	public static final String HRM021 = "hrm021";
	public static final String HRM022 = "hrm022";
	public static final String HRM030 = "hrm030";
	public static final String HRM031 = "hrm031";
	public static final String HRM032 = "hrm032";
	public static final String HRM040 = "hrm040";
	public static final String HRM041 = "hrm041";
	public static final String HRM042 = "hrm042";
	public static final String HRM110 = "hrm110";
	public static final String HRM111 = "hrm111";
	public static final String HRM112 = "hrm112";
	public static final String HRM210 = "hrm210";
	public static final String HRM410 = "hrm410";
	public static final String HRM411 = "hrm411";
	public static final String HRM412 = "hrm412";
	public static final String HRM510 = "hrm510";
	public static final String HRM511 = "hrm511";
	public static final String HRM512 = "hrm512";
	public static final String HRM520 = "hrm520";
	public static final String HRM521 = "hrm521";
	public static final String HRM522 = "hrm522";
	public static final String HRM420 = "hrm420";
	public static final String HRM421 = "hrm421";
	public static final String HRM422 = "hrm422";
	public static final String HRM610 = "hrm610";
	public static final String HRM620 = "hrm620";

	public static final String MSG010 = "msg010";
	public static final String MSG011 = "msg011";

	public static final String INV010 = "inv010";
	public static final String INV011 = "inv011";
	public static final String INV012 = "inv012";
	public static final String INV013 = "inv013";
	public static final String INV020 = "inv020";
	public static final String INV021 = "inv021";
	public static final String INV022 = "inv022";
	public static final String INV030 = "inv030";
	public static final String INV031 = "inv031";
	public static final String INV032 = "inv032";
	public static final String INV033 = "inv033";
	public static final String INV040 = "inv040";
	public static final String INV041 = "inv041";
	public static final String INV042 = "inv042";
	public static final String INV050 = "inv050";
	public static final String INV051 = "inv051";
	public static final String INV052 = "inv052";
	public static final String INV060 = "inv060";
	public static final String INV061 = "inv061";
	public static final String INV062 = "inv062";
	public static final String INV110 = "inv110";
	public static final String INV120 = "inv120";
	public static final String INV121 = "inv121";
	public static final String INV122 = "inv122";
	public static final String INV130 = "inv130";
	public static final String INV210 = "inv210";
	public static final String INV211 = "inv211";
	public static final String INV212 = "inv212";
	public static final String INV220 = "inv220";
	public static final String INV221 = "inv221";
	public static final String INV222 = "inv222";
	public static final String INV230 = "inv230";
	public static final String INV231 = "inv231";
	public static final String INV232 = "inv232";

	public static final String OFC010 = "ofc010";
	public static final String OFC110 = "ofc110";
	public static final String OFC111 = "ofc111";
	public static final String OFC112 = "ofc112";

	public static final String PUR000 = "pur000";
	public static final String PUR010 = "pur010";
	public static final String PUR011 = "pur011";
	public static final String PUR012 = "pur012";
	public static final String PUR020 = "pur020";
	public static final String PUR021 = "pur021";
	public static final String PUR022 = "pur022";
	public static final String PUR030 = "pur030";
	public static final String PUR031 = "pur031";
	public static final String PUR032 = "pur032";
	public static final String PUR040 = "pur040";
	public static final String PUR041 = "pur041";
	public static final String PUR042 = "pur042";
	public static final String PUR110 = "pur110";
	public static final String PUR111 = "pur111";
	public static final String PUR112 = "pur112";
	public static final String PUR113 = "pur113";
	public static final String PUR120 = "pur120";
	public static final String PUR121 = "pur121";
	public static final String PUR122 = "pur122";
	public static final String PUR130 = "pur130";
	public static final String PUR131 = "pur131";
	public static final String PUR132 = "pur132";
	public static final String PUR210 = "pur210";
	public static final String PUR211 = "pur211";
	public static final String PUR212 = "pur212";
	public static final String PUR220 = "pur220";
	public static final String PUR221 = "pur221";
	public static final String PUR222 = "pur222";
	public static final String PUR310 = "pur310";
	public static final String PUR311 = "pur311";
	public static final String PUR312 = "pur312";
	public static final String PUR313 = "pur313";

	public static final String SLE010 = "sle010";
	public static final String SLE011 = "sle011";
	public static final String SLE012 = "sle012";
	public static final String SLE020 = "sle020";
	public static final String SLE021 = "sle021";
	public static final String SLE022 = "sle022";
	public static final String SLE030 = "sle030";
	public static final String SLE031 = "sle031";
	public static final String SLE032 = "sle032";
	public static final String SLE040 = "sle040";
	public static final String SLE110 = "sle110";
	public static final String SLE111 = "sle111";
	public static final String SLE112 = "sle112";
	public static final String SLE120 = "sle120";
	public static final String SLE121 = "sle121";
	public static final String SLE122 = "sle122";
	public static final String SLE130 = "sle130";
	public static final String SLE131 = "sle131";
	public static final String SLE132 = "sle132";
	public static final String SLE140 = "sle140";
	public static final String SLE141 = "sle141";
	public static final String SLE142 = "sle142";
	public static final String SLE150 = "sle150";
	public static final String SLE151 = "sle151";
	public static final String SLE152 = "sle152";
	public static final String SLE160 = "sle160";
	public static final String SLE161 = "sle161";
	public static final String SLE162 = "sle162";
	public static final String SLE210 = "sle210";
	public static final String SLE211 = "sle211";
	public static final String SLE212 = "sle212";

	public static final String SVC010 = "svc010";
	public static final String SVC011 = "svc011";
	public static final String SVC012 = "svc012";
	public static final String SVC013 = "svc013";
	public static final String SVC110 = "svc110";
	public static final String SVC111 = "svc111";
	public static final String SVC112 = "svc112";
	public static final String SVC120 = "svc120";
	public static final String SVC121 = "svc121";
	public static final String SVC122 = "svc122";
	public static final String SVC130 = "svc130";
	public static final String SVC131 = "svc131";
	public static final String SVC132 = "svc132";
	public static final String SVC140 = "svc140";
	public static final String SVC141 = "svc141";
	public static final String SVC142 = "svc142";
	public static final String SVC150 = "svc150";
	public static final String SVC151 = "svc151";
	public static final String SVC152 = "svc152";
	public static final String SVC160 = "svc160";
	public static final String SVC161 = "svc161";
	public static final String SVC162 = "svc162";
	public static final String SVC170 = "svc170";
	public static final String SVC171 = "svc171";
	public static final String SVC172 = "svc172";
	public static final String SVC210 = "svc210";
	public static final String SVC020 = "svc020";
	public static final String SVC021 = "svc021";
	public static final String SVC022 = "svc022";
	public static final String SVC030 = "svc030";
	public static final String SVC031 = "svc031";
	public static final String SVC032 = "svc032";
	public static final String SVC040 = "svc040";
	public static final String SVC041 = "svc041";
	public static final String SVC042 = "svc042";

	public static final String TPL010 = "tpl010";
	public static final String TPL011 = "tpl011";
	public static final String TPL012 = "tpl012";
	public static final String TPL110 = "tpl110";
	public static final String TPL111 = "tpl111";
	public static final String TPL112 = "tpl112";
	public static final String TPL120 = "tpl120";
	public static final String TPL121 = "tpl121";
	public static final String TPL122 = "tpl122";
	public static final String TPL130 = "tpl130";
	public static final String TPL131 = "tpl131";
	public static final String TPL132 = "tpl132";
	public static final String TPL140 = "tpl140";
	public static final String TPL141 = "tpl141";
	public static final String TPL142 = "tpl142";
	public static final String TPL150 = "tpl150";
	public static final String TPL151 = "tpl151";
	public static final String TPL152 = "tpl152";

	public static final String WRK010 = "wrk010";
	public static final String WRK011 = "wrk011";
	public static final String WRK012 = "wrk012";
	public static final String WRK020 = "wrk020";
	public static final String WRK021 = "wrk021";
	public static final String WRK022 = "wrk022";
	public static final String WRK030 = "wrk030";
	public static final String WRK031 = "wrk031";
	public static final String WRK032 = "wrk032";
	public static final String WRK040 = "wrk040";
	public static final String WRK041 = "wrk041";
	public static final String WRK042 = "wrk042";
	public static final String WRK050 = "wrk050";
	public static final String WRK051 = "wrk051";
	public static final String WRK052 = "wrk052";
	public static final String WRK210 = "wrk210";
	public static final String WRK220 = "wrk220";
	public static final String WRK230 = "wrk230";
	public static final String WRK240 = "wrk240";

	public static final String IM0001 = "IM0001";
	public static final String IM0002 = "IM0002";
	public static final String IM0003 = "IM0003";
	public static final String IM0004 = "IM0004";
	public static final String IM0011 = "IM0011";

	public static final String CM0001 = "CM0001";
	public static final String CM0002 = "CM0002";
	public static final String CM0003 = "CM0003";
	public static final String CM0004 = "CM0004";
	public static final String CM0005 = "CM0005";
	public static final String CM0006 = "CM0006";
	public static final String CM0007 = "CM0007";
	public static final String CM0008 = "CM0008";
	public static final String CM0009 = "CM0009";
	public static final String CM0010 = "CM0010";
	public static final String CM0011 = "CM0011";
	public static final String CM0012 = "CM0012";
	public static final String CM0013 = "CM0013";
	public static final String CM0014 = "CM0014";
	public static final String CM0015 = "CM0015";
	public static final String CM0016 = "CM0016";
	public static final String CM0017 = "CM0017";
	public static final String CM0018 = "CM0018";
	public static final String CM0019 = "CM0019";
	public static final String CM0020 = "CM0020";
	public static final String CM0021 = "CM0021";

	public static final String CM1006 = "CM1006";
	public static final String CM1007 = "CM1007";
	public static final String CM1008 = "CM1008";
	public static final String CM1009 = "CM1009";
	public static final String CM1010 = "CM1010";

	public static final String EM0001 = "EM0001";
	public static final String EM0002 = "EM0002";
	public static final String EM0003 = "EM0003";
	public static final String EM0004 = "EM0004";
	public static final String EM0005 = "EM0005";
	public static final String EM0006 = "EM0006";
	public static final String EM0007 = "EM0007";
	public static final String EM0008 = "EM0008";
	public static final String EM0009 = "EM0009";
	public static final String EM0010 = "EM0010";
	public static final String EM0011 = "EM0011";
	public static final String EM0012 = "EM0012";
	public static final String EM0013 = "EM0013";
	public static final String EM0014 = "EM0014";
	public static final String EM0015 = "EM0015";
	public static final String EM0016 = "EM0016";
	public static final String EM0017 = "EM0017";
	public static final String EM0018 = "EM0018";
	public static final String EM0019 = "EM0019";
	public static final String EM0020 = "EM0020";
	public static final String EM0021 = "EM0021";
	public static final String EM0022 = "EM0022";
	public static final String EM0023 = "EM0023";
	public static final String EM0024 = "EM0024";
	public static final String EM0025 = "EM0025";
	public static final String EM0026 = "EM0026";
	public static final String EM0027 = "EM0027";
	public static final String EM0028 = "EM0028";
	public static final String EM0029 = "EM0029";
	public static final String EM0030 = "EM0030";
	public static final String EM0031 = "EM0031";
	public static final String EM0032 = "EM0032";
	public static final String EM0033 = "EM0033";
	public static final String EM0034 = "EM0034";
	public static final String EM0035 = "EM0035";
	public static final String EM0036 = "EM0036";
	public static final String EM0037 = "EM0037";
	public static final String EM0038 = "EM0038";
	public static final String EM0039 = "EM0039";
	public static final String EM0040 = "EM0040";
	public static final String EM0041 = "EM0041";
	public static final String EM0042 = "EM0042";
	public static final String EM0043 = "EM0043";
	public static final String EM0044 = "EM0044";
	public static final String EM0045 = "EM0045";
	public static final String EM0046 = "EM0046";
	public static final String EM0047 = "EM0047";
	public static final String EM0048 = "EM0048";
	public static final String EM0049 = "EM0049";
	public static final String EM0050 = "EM0050";

	private boolean isPopupPage = false;

	public BaseBean() {
		super();

		try {
			isPopupPage = Lib.toBoolean(getParamPage(POPUP_PAGE), false);
			initPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void initPage() throws Exception;

	public boolean isPopupPage() {
		return isPopupPage;
	}

	public boolean isPermission(String pageId) {
		PageDto pageDto = getDto(PAGE_DTO);
		String bussiness = pageId != null ? pageId.substring(0, pageId.length() - 1) : "";
		List<Menu> mainMenu = pageDto.getMainMenu();
		List<Menu> subMenu = null;
		boolean ret = false;
		if (mainMenu != null) {
			for (int i = 0; i < mainMenu.size(); i++) {
				subMenu = mainMenu.get(i).getSubMenu();
				Menu menu = null;
				Page page = null;
				if (subMenu != null) {
					for (int j = 0; j < subMenu.size(); j++) {
						menu = subMenu.get(j);
						page = menu.getPage();
						if (page != null && bussiness.equals(page.getBusiness())) {
							ret = true;
							break;
						}
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Go to next page id
	 * 
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public String goPage(String pageId) throws Exception {
		return goPage(pageId, getPageCode(), false);
	}

	public String goPage(String pageId, boolean cache) throws Exception {
		return goPage(pageId, getPageCode(), cache);
	}

	public String goPage(String pageId, String fromPage) throws Exception {
		return goPage(pageId, fromPage, false);
	}

	public String goPage(String pageId, String fromPage, boolean cache) throws Exception {
		if (getPageCode().equals(pageId) || "".equals(pageId.trim())) {
			pageId = null;
		}
		if (!isPermission(pageId)) {
			messageDto.showError("Access denied.");
			return null;
		}

		putParamPage(FROM_PAGE, fromPage);
		putParamPage(POPUP_PAGE, isPopupPage);
		if (cache) {
			putCacheDto();
		}
		return pageId;
	}

	/**
	 * Redirect to page
	 * 
	 * @param pageId
	 * @return
	 */
	public String redirectPage(String pageId) {
		if (getPageCode().equals(pageId) || "".equals(pageId)) {
			return null;
		}
		return pageId.concat("?faces-redirect=true");
	}

	/**
	 * Popup page
	 * 
	 * @param pageId
	 */
	public void popupPage(String pageId) {
		popupPage(pageId, (String) null, (String) null, false, 0, 0);
	}

	public void popupPage(String pageId, boolean isModal) {
		popupPage(pageId, (String) null, (String) null, isModal, 0, 0);
	}

	public void popupPage(String pageId, String handleReturn) {
		popupPage(pageId, handleReturn, (String) null, false, 0, 0);
	}

	public void popupPage(String pageId, int height, int width) {
		popupPage(pageId, (String) null, (String) null, false, height, width);
	}

	public void popupPage(String pageId, boolean isModal, int height, int width) {
		popupPage(pageId, (String) null, (String) null, isModal, height, width);
	}

	public void popupPage(String pageId, String handleReturn, int height, int width) {
		popupPage(pageId, handleReturn, (String) null, false, height, width);
	}

	public void popupPage(String pageId, String handleReturn, boolean isModal) {
		popupPage(pageId, handleReturn, (String) null, isModal, 0, 0);
	}

	public void popupPage(String pageId, String handleReturn, boolean isModal, int height, int width) {
		popupPage(pageId, handleReturn, (String) null, isModal, height, width);
	}

	public void popupPage(String pageId, String handleReturn, String title) {
		popupPage(pageId, handleReturn, title, false, 0, 0);
	}

	public void popupPage(String pageId, String handleReturn, String title, boolean isModal) {
		popupPage(pageId, handleReturn, title, isModal, 0, 0);
	}

	public void popupPage(String pageId, String handleReturn, String title, boolean isModal, int height, int width) {
		if (Lib.isEmpty(pageId)) {
			return;
		}

		putParamPage(FROM_PAGE, getPageCode());
		putParamPage(POPUP_PAGE, true);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		String pageUrl = "/page/" + pageId.substring(0, 3) + "/" + pageId + ".html";

		// This is the proper way to get the view's url
		ViewHandler viewHandler = facesContext.getApplication().getViewHandler();
		String popupUrl = viewHandler.getActionURL(facesContext, "/popup.html");
		pageUrl = viewHandler.getActionURL(facesContext, pageUrl);

		String windowName = getPageCode() + pageId;

		String openNewWindow = "openNewWindow(window,'" + popupUrl + "','" + windowName + "','" + handleReturn + "',"
				+ isModal + "," + height + "," + width + ");";

		facesContext.getExternalContext().getRequestMap().put("openNewWindow", openNewWindow);

		if (Lib.isEmpty(title)) {
			title = Lib.getResource(locale, pageId + ".title");
		}
		putParamPage(PopupBean.TITLE, title);
		putParamPage(PopupBean.WINDOW_NAME, windowName);
		putParamPage(PopupBean.PAGE_URL, pageUrl);
		putParamPage(PopupBean.HANDLE_RETURN, handleReturn);
	}

	/**
	 * View report file
	 * 
	 * @param data
	 * @param fileName
	 */
	public void pdfViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_PDF;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_PDF)) {
				fileName += Constant.EXTENSION_PDF;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_PDF);
	}

	public void xlsViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_XLS;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_XLS)) {
				fileName += Constant.EXTENSION_XLS;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_XLS);
	}

	public void jxlViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_JXL;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_JXL)) {
				fileName += Constant.EXTENSION_JXL;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_JXL);
	}

	public void csvViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_CSV;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_CSV)) {
				fileName += Constant.EXTENSION_CSV;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_CSV);
	}

	public void odtViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_ODT;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_ODT)) {
				fileName += Constant.EXTENSION_ODT;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_ODT);
	}

	public void odsViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_ODS;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_ODS)) {
				fileName += Constant.EXTENSION_ODS;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_ODS);
	}

	public void docxViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_DOCX;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_DOCX)) {
				fileName += Constant.EXTENSION_DOCX;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_DOCX);
	}

	public void xlsxViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_XLSX;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_XLSX)) {
				fileName += Constant.EXTENSION_XLSX;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_XLSX);
	}

	public void pptxViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_PPTX;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_PPTX)) {
				fileName += Constant.EXTENSION_PPTX;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_PPTX);
	}

	public void xmlViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_XML;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_XML)) {
				fileName += Constant.EXTENSION_XML;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_XML);
	}

	public void htmlViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_HTML;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_HTML)) {
				fileName += Constant.EXTENSION_HTML;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_HTML);
	}

	public void rtfViewReport(byte[] data, String fileName) {
		if (Lib.isEmpty(fileName)) {
			fileName = getPageCode() + Constant.EXTENSION_RTF;
		} else {
			if (!fileName.endsWith(Constant.EXTENSION_RTF)) {
				fileName += Constant.EXTENSION_RTF;
			}
		}
		viewReport(data, fileName, Constant.CONTENT_TYPE_RTF);
	}

	/**
	 * View report file
	 * 
	 * @param data
	 * @param fileName
	 * @param contentType
	 */
	public void viewReport(byte[] data, String fileName, String contentType) {
		viewReport(data, fileName, contentType, Constant.ENCODING);
	}

	/**
	 * View report file
	 * 
	 * @param data
	 * @param contentType
	 * @param fileName
	 * @param encode
	 */
	public void viewReport(byte[] data, String fileName, String contentType, String encoding) {
		if (data == null) {
			messageDto.showError(Lib.getMessage(locale, "EM2001"));
			return;
		}
		putParamPage(REPORT_DATA, data);
		putParamPage(REPORT_FILE_NAME, fileName);
		putParamPage(REPORT_CONTENT_TYPE, contentType);
		putParamPage(REPORT_ENCODING, encoding);

		// Open window view report
		popupPage(CMN000, null, fileName, true);
	}

	public void returnFromDialog() {

	}

	/**
	 * Go to previous page data table
	 * 
	 * @throws Exception
	 */
	public void prevPage() throws Exception {
		TableDto dto = getDto();
		TableData tableData = dto.getTableData();
		dto.setDataTable(tableData.prevPageData());
	}

	/**
	 * Go to next page data table
	 * 
	 * @throws Exception
	 */
	public void nextPage() throws Exception {
		TableDto dto = getDto();
		TableData tableData = dto.getTableData();
		dto.setDataTable(tableData.nextPageData());
	}

	/**
	 * Go to selected page data table
	 * 
	 * @throws Exception
	 */
	public void selectPage() throws Exception {
		TableDto dto = getDto();
		TableData tableData = dto.getTableData();
		dto.setDataTable(tableData.getPageData());
	}

	/**
	 * Back to previous Business
	 * 
	 * @return
	 * @throws Exception
	 */
	public String prevBusinessAction() throws Exception {
		Dto dto = getDto();
		putParamPage(ID, dto.getPrevBusinessId());
		return goPage(dto.getPrevBusinessCode() + "1");
	}

	/**
	 * Go to next Business
	 * 
	 * @return
	 * @throws Exception
	 */
	public String nextBusinessAction() throws Exception {
		Dto dto = getDto();
		putParamPage(FROM_BUSINESS_CODE, getBussinessCode());
		putParamPage(FROM_BUSINESS_ID, dto.getId());
		putParamPage(BUSINESS_ADD_MORE, dto.isNextBusinessAddMore());
		String nextPage = null;

		if (dto.isNextBusinessAddMore()) {
			nextPage = dto.getNextBusinessCode() + "2";
		} else {
			doService("nextBusiness", dto);
			System.out.println("NextBusinessView = " + dto.isNextBusinessView());
			if (dto.isNextBusinessView()) {
				nextPage = dto.getNextBusinessCode() + "1";
				putParamPage(ID, dto.getNextBusinessId());
				System.out.println("View 1 = " + dto.getNextBusinessId());
			} else {
				nextPage = dto.getNextBusinessCode() + "2";
				System.out.println("View 2 = " + dto.getNextBusinessId());
			}
		}

		return goPage(nextPage, true);
	}

	/**
	 * copy New Action
	 * 
	 * @throws Exception
	 */
	public String cloneAction() throws Exception {
		Dto dto = getDto();
		String pageCode = dto.getPageCode();
		String bussinessCode = dto.getBusinessCode();
		System.out.println("pageCode = " + pageCode);
		if ("2".equals(Lib.right(pageCode, 1))) {
			System.out.println("id2 = " + dto.getId());
			dto.setPrevBusinessId(dto.getId());
			dto.setId(null);
			doService("initPage", dto);
			return null;
		} else if ("1".equals(Lib.right(pageCode, 1))) {
			System.out.println("id1 = " + dto.getId());
			putParamPage(FROM_BUSINESS_ID, dto.getId());
			putParamPage(ID, null);
			return goPage(bussinessCode + "2");
		}
		return null;
	}

	/**
	 * copy New Action
	 * 
	 * @throws Exception
	 */
	// public String copyNewAction() throws Exception {
	// Dto dto = getDto();
	// putParamPage(FROM_BUSINESS_ID, dto.getId());
	// putParamPage(FROM_PAGE, null);
	// putParamPage(ID, null);
	// System.out.println("fromPage0 = " + getParamPage(FROM_PAGE));
	// System.out.println("FROM_BUSINESS_ID = " + dto.getId());
	// System.out.println("ID = " + getParamPage(ID));
	// System.out.println("getBussinessCode = " + getBussinessCode() + "2");
	// return goPage(getBussinessCode() + "2");
	// }

	/**
	 * To do standard action
	 * 
	 * @return
	 * @throws Exception
	 */
	// public void standardAction() throws Exception {
	// Dto dto = getDto();
	// System.out.println("getStandardAction1 = " + dto.getStandardAction());
	// }

	public void viewExportReport(byte[] data, String fileName, String standarType) {
		if (Constant.EXPORT_PDF.equals(standarType)) {
			pdfViewReport(data, fileName);
		} else if (Constant.EXPORT_XLSX.equals(standarType)) {
			xlsxViewReport(data, fileName);
		} else if (Constant.EXPORT_CSV.equals(standarType)) {
			csvViewReport(data, fileName);
		} else if (Constant.EXPORT_DOCX.equals(standarType)) {
			docxViewReport(data, fileName);
		} else if (Constant.EXPORT_IMAGE.equals(standarType)) {
			rtfViewReport(data, fileName);
		} else if (Constant.EXPORT_HTML.equals(standarType)) {
			htmlViewReport(data, fileName);
		}
	}

	public void viewExportStandard(byte[] data, String fileName, String standarType) {
		String contentType = null;
		if (Constant.EXPORT_PDF.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_PDF;
			fileName = fileName + Constant.EXTENSION_PDF;
		} else if (Constant.EXPORT_XLSX.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_XLSX;
			fileName = fileName + Constant.EXTENSION_XLSX;
		} else if (Constant.EXPORT_CSV.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_CSV;
			fileName = fileName + Constant.EXTENSION_CSV;
		} else if (Constant.EXPORT_DOCX.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_DOCX;
			fileName = fileName + Constant.EXTENSION_DOCX;
		} else if (Constant.EXPORT_IMAGE.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_JPEG;
			fileName = fileName + Constant.EXTENSION_JPEG;
		} else if (Constant.EXPORT_HTML.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_HTML;
			fileName = fileName + Constant.EXTENSION_HTML;
		} else if (Constant.EXPORT_RTF.equals(standarType)) {
			contentType = Constant.CONTENT_TYPE_RTF;
			fileName = fileName + Constant.EXTENSION_RTF;
		}

		download(data, contentType, fileName, true);
	}

	public boolean isValidCustomer(Long id, String name) {
		Customer customer = findEntity(Customer.class, id);
		if (customer != null && customer.getName().equals(name)) {
			return true;
		}
		return false;
	}

	/**
	 * Download string
	 * 
	 * @param content
	 * @param contentType
	 * @param fileName
	 * @param attachment
	 * @param encode
	 */
	public void download(String content, String contentType, String fileName, boolean attachment, String encoding) {
		String contentDisposition = (attachment ? "attachment;" : "") + "filename=" + fileName;
		download(content, contentType, contentDisposition, encoding);
	}

	public void download(String content, String contentType, String contentDisposition, String encoding) {
		if (content == null) {
			return;
		}

		contentDisposition = contentDisposition == null ? "" : contentDisposition;
		encoding = encoding == null ? Constant.ENCODING : encoding;

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType(contentType);
		response.setCharacterEncoding(encoding);
		response.setHeader("Content-disposition", contentDisposition);

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * Download data
	 * 
	 * @param content
	 * @param contentType
	 * @param fileName
	 * @param attachment
	 * @param encode
	 */
	public void download(byte[] content, String contentType, String fileName, boolean attachment, String encoding) {
		String contentDisposition = (attachment ? "attachment;" : "") + "filename=" + fileName;
		download(content, contentType, contentDisposition, encoding);
	}

	public void download(byte[] content, String contentType, String fileName, boolean attachment) {
		String contentDisposition = (attachment ? "attachment;" : "") + "filename=" + fileName;
		download(content, contentType, contentDisposition, null);
	}

	public void download(byte[] content, String contentType, String contentDisposition, String encoding) {
		if (content == null) {
			return;
		}
		contentDisposition = contentDisposition == null ? "" : contentDisposition;
		encoding = encoding == null ? Constant.ENCODING : encoding;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ctx = fc.getExternalContext();
		ctx.responseReset();

		ctx.setResponseContentType(contentType);
		ctx.setResponseCharacterEncoding(encoding);
		ctx.setResponseHeader("Content-disposition", contentDisposition);
		ctx.setResponseContentLength(content.length);
		OutputStream os = null;
		try {
			os = ctx.getResponseOutputStream();
			os.write(content);
			os.flush();
			fc.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param host
	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param content
	 * @param attachments
	 * @return
	 */
	public boolean sendMail(String host, String from, String to, String cc, String bcc, String subject, String content,
			String... attachments) {
		try {
			// Get system properties
			InputStream input = getClass().getClassLoader().getResourceAsStream("Mail.properties");
			Properties properties = System.getProperties();

			properties.load(input);

			if (Lib.isNotEmpty(host)) {
				// Setup mail server
				properties.setProperty("mail.smtp.host", host);
			}

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set the RFC 822 "From" header field using the
			// value of the InternetAddress.getLocalAddress method.
			message.setFrom(new InternetAddress(from));

			// Add the given addresses to the specified recipient type.
			message.addRecipients(Message.RecipientType.TO, to);

			if (Lib.isNotEmpty(cc)) {
				message.addRecipients(Message.RecipientType.CC, cc);
			}

			if (Lib.isNotEmpty(bcc)) {
				message.addRecipients(Message.RecipientType.BCC, cc);
			}

			message.setHeader("Content-Type", "text/plain;charset=UTF-8");

			// Set the "SendDate" header field.
			message.setSentDate(new Date());

			// Set the "Subject" header field.
			message.setSubject(subject, Constant.ENCODING);

			if (attachments == null || attachments.length == 0) {
				// Sets the given String as this part's content,
				// with a MIME type of "text/plain".
				message.setText(content, Constant.ENCODING);

			} else {
				// Create a message part to represent the body text
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(content);

				// use a MimeMultipart as we need to handle the file attachments
				Multipart multipart = new MimeMultipart();

				// add the message body to the mime message
				multipart.addBodyPart(messageBodyPart);

				// add any file attachments to the message
				addAtachments(attachments, multipart);

				// Put all message parts in the message
				message.setContent(multipart, Constant.ENCODING);
			}

			// Send the message
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private void addAtachments(String[] attachments, Multipart multipart) throws Exception {
		for (int i = 0; i <= attachments.length - 1; i++) {
			String filename = attachments[i];
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();

			// use a JAF FileDataSource as it does MIME type detection
			DataSource source = new FileDataSource(filename);
			attachmentBodyPart.setDataHandler(new DataHandler(source));

			// assume that the filename you want to send is the same as the
			// actual file name - could alter this to remove the file path
			attachmentBodyPart.setFileName(filename);

			// add the attachment
			multipart.addBodyPart(attachmentBodyPart);
		}
	}

	/**
	 * Get the folder directory of the reports
	 * 
	 * @author TuanLA17
	 * @return
	 */
	protected String getReportFolder() throws FileNotFoundException {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append("WEB-INF/classes/reports");

		return context.getRealPath(pathBuilder.toString()) + "\\";
	}

	protected void writePdfFileToResponse(byte[] data) throws IOException {
		if (data != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setContentLength(data.length);

			ServletOutputStream servletOut = response.getOutputStream();
			servletOut.write(data);
			servletOut.flush();
			servletOut.close();

			facesContext.responseComplete();
		}
	}

	public String getFileNames(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
