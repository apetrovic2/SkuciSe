using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.DAL.Interfaces
{
    public interface IUserDAL
    {
        List<User> GetAllUsers();
        Task<int> RegistrationAsync(User user);
        int Login(String username, String password);
        int EditUser(int id, String username, String password, String name, String email);
        int DeleteUser(int id);
        User GetUserById(int id);
        int ChangePassword(int id, String hash, String salt);
    }
}
