using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI.Interfaces
{
    public interface IUserUI
    {
        List<User> GetAllUsers();
        int Registration(String username, String password, String name, String email);
        int Login(String username, String password);
        User GetUserById(int id);
    }
}
