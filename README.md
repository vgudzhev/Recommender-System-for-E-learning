#	Recommender System for e-learning

##	Abstract: With the fast growth of the Internet and e-services, the E-learning establish itself increasingly as an alternative and in support of the traditional education. Multiple education platforms full with online courses are created with the main purpose to educate the users interactively and to provide practical trainings. More and more books and papers are avaiable online. Despite of the fact that the there is a hude amount of learning materials on a given topic it often make dificulties in front of the users to make a good decision because sometimes they are not even suspect that useful learning content exists.

##	Motivation: In this web app the mentioned problem is addressed by a recommender systems. Their primary task is to make useful suggestions to the the users containing educational content and to save users time in this way. The suggestions is it likely to be very useful for the user because are precomputed on its past preference and past behaviuor basis. Recommender Systems are software tools and techniques, providing suggestions for items that can be very useful for the users. The suggestions helps the user to make a different decisions such as what items to buy, what kind of music to listen, or what books to read. Recommeder systems are very useful for users without much experience and cannot make a decisions alone without additional help. In its simplest form recommender systems are sorted lists with user`s preferences. And the sorting is carried out by users`s past rating and behaviour. The main task of a recommedenr system is to collect information about the users and about their preferences. The more preference has been made, the more accurate the system will be.

##	Used technologies: Java 1.7, Play Framework 2.3.5 Ebean, SBT, H2 database/MySQL, Apache Mahout

## Instructions:

git clone github.com/vgudzhev/ssrs open command prompt and navigate to the cloned repo type->

activator cleanFiles activator run -> The web app is ready; ** If you want to use MySQL, not H2base you must uncoment the following lines from root/conf/application.conf: db.default.driver=com.mysql.jdbc.Driver db.default.url=“jdbc:mysql://127.0.0.1:3306/ssrs” db.default.user=“root” db.default.pass=“pass”

## Default credentials: admin-user: admin@mail.bg password: pass
