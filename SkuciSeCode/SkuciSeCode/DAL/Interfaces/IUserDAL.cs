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
        User GetUserById(int id);
    }
}
