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
        int EditUser(int id, String username, String password, String name, String email);
        int Login(String username, String password);
        int DeleteUser(int id);
        User GetUserById(int id);
    }
}
