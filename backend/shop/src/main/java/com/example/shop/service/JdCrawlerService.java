package com.example.shop.service;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JdCrawlerService {
    private final ProductRepository productRepo;

    public JdCrawlerService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> crawlAndSave(String keyword, int maxPage) {
        List<Product> products = new ArrayList<>();
        Random rand = new Random();
        try {
            for (int page = 1; page <= maxPage * 2; page += 2) {
                String url = "https://search.jd.com/Search?keyword=" +
                        URLEncoder.encode(keyword, StandardCharsets.UTF_8) +
                        "&page=" + page;
                System.out.println("[INFO] 抓取：" + url);

                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0")
                        .timeout(10000)
                        .get();

                Elements goods = doc.select(".gl-item");
                for (Element g : goods) {
                    Product p = new Product();
                    p.setTitle(g.select(".p-name em").text());

                    String priceStr = g.select(".p-price i").text();
                    double price = 0.0;
                    try { price = Double.parseDouble(priceStr); } catch (Exception ignored) {}
                    p.setPrice(price);
                    p.setOldPrice(price + rand.nextInt(300));

                    p.setShopName(g.select(".p-shop span").text());

                    String img = g.select(".p-img img").attr("data-lazy-img");
                    if (img.isEmpty()) img = g.select(".p-img img").attr("src");
                    if (!img.startsWith("http")) img = "https:" + img;
                    p.setImage(img);

                    p.setCommentCount(50 + rand.nextInt(5000));
                    p.setGoodRate((95 + rand.nextInt(5)) + "%");
                    p.setTags(p.getShopName().contains("京东") ? "自营,热卖" : "热卖");

                    products.add(p);
                }
                Thread.sleep(1500);
            }
            productRepo.saveAll(products);
            System.out.println("[SUCCESS] 保存商品数：" + products.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
