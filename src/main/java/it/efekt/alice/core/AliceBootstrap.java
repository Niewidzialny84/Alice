package it.efekt.alice.core;

import it.efekt.alice.commands.database.Database;
import it.efekt.alice.db.GuildConfig;
import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AliceBootstrap {

public static Alice alice;
public final static Logger logger = LoggerFactory.getLogger(AliceBootstrap.class);
public static final int EMBED_COLOR = 15648332;
public static Database db;
public static SessionFactory sessionFactory;


    public static void main(String[] args) {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(GuildConfig.class)
                .buildSessionFactory();

       // Session session = factory.getCurrentSession();

//        try {
//            GuildConfig guildConf = new GuildConfig("01", "!b");
//            session.beginTransaction();
//            session.save(guildConf);
//            session.getTransaction().commit();



//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            GuildConf guildConf = session.get(GuildConf.class, "01");
//            System.out.println("ZNALAZLEM: " + guildConf.getPrefix());
//            session.getTransaction().commit();

//
//            session.beginTransaction();
//            List<GuildConf> conf = session.createQuery("from GuildConf c where c.prefix = '<'").getResultList();
//            conf.stream().forEach(System.out::println);
//            session.getTransaction().commit();


//            session.beginTransaction();
//            GuildConf guildConf = session.get(GuildConf.class, "01");
//            guildConf.setPrefix("joł");
//            session.getTransaction().commit();

//        } finally {
//            factory.close();
//        }
        init();
    }
    // Checks if there is config.yml file in the directory of .jar
    // if not, it creates a default config file
    // if correct token is provided, proceed to starting the bot
    private static void init(){

        Yaml yaml = new Yaml();
        Config config;

        try {
            logger.info("Looking for config.yml file...");
            if (!Files.exists(Paths.get("./config.yml"))){
                InputStream in = AliceBootstrap.class.getClassLoader().getResourceAsStream("config.yml"); //todo change this to config-default.yml before releasing
                try (OutputStream outputStream = new FileOutputStream(new File("./config.yml"))) {
                    logger.error("Didn't find config.yml file, copying default one...");
                    IOUtils.copy(in, outputStream);
                } catch (NullPointerException exc){
                    exc.printStackTrace();
                }
            }
            logger.info("Loading config file...");
            InputStream in = Files.newInputStream(Paths.get("./config.yml"));
            config = yaml.loadAs(in, Config.class);
            logger.info("Config loaded: \n" + config.toString());

            alice = new Alice(config);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}