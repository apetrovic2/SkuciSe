using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class User
    {
        public int id { get; set; }
        public String username { get; set; }
        public String hash { get; set; }
        public String salt { get; set; }
        public String name { get; set; }
        public String email { get; set; }

        public User()
        {

        }

        public User(String username, String hash, String salt, String name, String email)
        {
            this.username = username;
            this.hash = hash;
            this.salt = salt;
            this.name = name;
            this.email = email;
        }

        public User(int id, String username, String hash, String salt, String name, String email)
        {
            this.id = id;
            this.username = username;
            this.hash = hash;
            this.salt = salt;
            this.name = name;
            this.email = email;
        }

    }
}
