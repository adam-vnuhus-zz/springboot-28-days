package com.onemount.onecinema.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.onemount.onecinema.model.Film;
import com.onemount.onecinema.model.Genre;
import com.onemount.onecinema.model.Language;
import com.onemount.onecinema.model.Rating;
import com.onemount.onecinema.repository.FilmRepository;
import com.onemount.onecinema.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
  private static final String DD_MM_YYYY = "dd/MM/yyyy";

  @PersistenceContext
  private EntityManager em;

  @Autowired private  GenreRepository genreRepo;
  @Autowired private  FilmRepository filmRepo;

  @Transactional
  public void generateSampleFilms() throws ParseException {
    Genre hanhDong = genreRepo.findByName("Hành động");
    Genre kinhDi = genreRepo.findByName("Kinh dị");
    Genre hai = genreRepo.findByName("Hài");
    Genre tinhCam = genreRepo.findByName("Tình cảm");
    Genre hoiHop = genreRepo.findByName("Hồi hộp");
    Genre tamLy = genreRepo.findByName("Tâm lý");
    Genre giaDinh = genreRepo.findByName("Gia đình");

    Film banTayDietQuy = Film.builder()
    .title("BÀN TAY DIỆT QUỶ")
    .description("Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành “người được chọn”.")
    .director("Kim Joo Hwan")
    .actors("Park Seo Joon, Ahn Sung Ki, Woo Do Hwan, Choi Woo Sik…")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/04/2021"))
    .language(Language.KR)
    .hasVietSubTitle(true)
    .lengthInMinutes(128)
    .rating(Rating.C18)
    .thumbnail("thumbnail")
    .build();

    banTayDietQuy.addGenre(hanhDong);
    banTayDietQuy.addGenre(kinhDi);

    Film palmSpring = Film.builder()
    .title("BỐ GIÀ")
    .description("Phim sẽ xoay quanh lối sống thường nhật của một xóm lao động nghèo, ở đó có bộ tứ anh em Giàu - Sang - Phú - Quý với Ba Sang sẽ là nhân vật chính, hay lo chuyện bao đồng nhưng vô cùng thương con cái. Câu chuyện phim tập trung về hai cha con Ba Sang (Trấn Thành) và Quắn (Tuấn Trần). Dù yêu thương nhau nhưng khoảng cách thế hệ đã đem đến những bất đồng lớn giữa hai cha con. Liệu cả hai có thể cho nhau cơ hội thấu hiểu đối phương, thu hẹp khoảng cách và tạo nên hạnh phúc từ sự khác biệt?")
    .director("Vũ Ngọc Đãng & Trấn Thành")
    .actors("Trấn Thành, Tuấn Trần, Ngân Chi, NSND Ngọc Giàu, Lê Giang, Lan Phương, Hoàng Mèo, La Thành, Quốc Khánh, Lê Trang, A Quay, Bảo Phúc,…")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("12/03/2021"))
    .language(Language.VN)
    .hasVietSubTitle(false)
    .lengthInMinutes(128)
    .rating(Rating.C13)
    .build();
    palmSpring.addGenre(hai);
    palmSpring.addGenre(giaDinh);


    Film trumCuoiSieuDang = Film.builder()
    .title("TRÙM CUỐI SIÊU ĐẲNG")
    .description("Mắc kẹt trong một vòng lặp thời gian ngay đúng ngày anh ta bị giết chết, một cựu đặc vụ Roy Pulver (Frank Gillo) đã phát hiện ra manh mới về một dự án bí mật của chính phủ có thể giải đáp bí ẩn đằng sau cái chết vô thời hạn của anh ta. Roy buộc lòng phải chạy đua với thời gian và truy bắt tên Colonel Ventor (Mel Gibson), đầu sỏ của dự án chính phủ này. Trong lúc đó, anh phải thoát khỏi cuộc vây bắt của những tên sát thủ tàn ác quyết tâm ngăn cản Roy tìm ra được sự thật. Liệu Roy Pulver có thể thoát khỏi vòng lặp này và cứu lấy gia đình đồng thời sống lại một lần nữa vào ngày mai?")
    .director("Joe Carnahan")
    .actors("Frank Grillo, Mel Gibson, Naomi Watts, Dương Tử Quỳnh, Annabelle Wallis, Ken Jeong, Will Sasso, ….")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("23/04/2021"))
    .language(Language.EN)
    .hasVietSubTitle(true)
    .lengthInMinutes(101)
    .rating(Rating.C18)
    .build();
    trumCuoiSieuDang.addGenre(hanhDong);
    trumCuoiSieuDang.addGenre(hoiHop);

    Film cucNoHoaCucCung = Film.builder()
    .title("CỤC NỢ HÓA CỤC CƯNG")
    .description("Du-seok (Sung Dong Il) và Jong-bae (Kim Hiewon) là hai gã chuyên đòi nợ thuê có máu mặt. Để uy hiếp một con nợ, cả hai đã bắt Seung-yi (Park Soi) - một bé gái 9 tuổi làm vật thế chấp cho số nợ của mẹ cô bé. Tuy nhiên, mẹ của Seung-yi lại bị trục xuất về nước, và hai ông chú đành nhận trách nhiệm trông chừng Seung-yi đến khi cô bé được một gia đình giàu có nhận nuôi. Khi phát hiện ra Seung-yi nhỏ bé bị bán đi làm công cho một bà chủ vô trách nhiệm, Du-seok đã tìm đến để chuộc lại cô bé. Mặc dù Seung-yi vốn là \"cục nợ\" Du-seok và Jong-bae không hề mong muốn, cô bé dần trở thành cục cưng yêu quý và cả 3 sống bên nhau như một gia đình.")
    .director("Kang Dae Kyu")
    .actors("Sung Dong Il, Ha Ji Won, Kim Hiewon, Park Soi")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/10/2020"))
    .language(Language.KR)
    .hasVietSubTitle(true)
    .lengthInMinutes(113)
    .rating(Rating.P)
    .build();

    cucNoHoaCucCung.addGenre(hai);
    cucNoHoaCucCung.addGenre(tamLy);

    em.persist(banTayDietQuy);
    em.persist(palmSpring);
    em.persist(trumCuoiSieuDang);
    em.persist(cucNoHoaCucCung);
  }

  public List<Film> findAll() {
    return filmRepo.findAll();
  }
}
