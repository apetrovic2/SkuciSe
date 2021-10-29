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
        public String password { get; set; }
        public String name { get; set; }
        public String email { get; set; }

        public User()
        {

        }

        public User(String username, String password, String name, String email)
        {
            this.username = username;
            this.password = password;
            this.name = name;
            this.email = email;
        }

    }
}
