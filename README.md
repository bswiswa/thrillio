# Thrill.io

Thrill.io is a social bookmarking web application created from Udemy course: Java In-Depth by Dheeru Mundluru. It is meant to introduce Java OOP concepts and
MVC concepts.

It has 4 types of users: User, EmailAdmin, Editor and ChiefEditor

These users can bookmark and share 3 kinds of media: Books, Movies and Web links

User functions:

**User**            : saveWebLink(), saveMovie(), saveBook(), rateBookmark(),
                        postAReview()[needs approval]

**EmailAdmin**      : saveWebLink(), saveMovie(), saveBook(), rateBookmark(),
                          postAReview()[no approval], handleEmailCampaign()

**Editor**          : saveWebLink(), saveMovie(), saveBook(), rateBookmark(),
                          postAReview()[no approval], approveReview(),
                          rejectReview()

**ChiefEditor**     : saveWebLink(), saveMovie(), saveBook(), rateBookmark(),
                          postAReview()[no approval], approveReview(),
                          rejectReview(), updateHomepage()

## Getting Started

Install Eclipse IDE

```
git clone https://github.com/bswiswa/thrillio.git

```
## Acknowledgments

* Dheeru Mundluru
