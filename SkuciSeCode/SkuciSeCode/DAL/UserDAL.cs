using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.DAL
{
    public class UserDAL : IUserDAL
    {
        private readonly UserDbContext _context;
        public UserDAL(UserDbContext context)
        {
            _context = context;
        }
        public List<User> GetAllUsers()
        {
            var list = _context.Users.ToList();
            return list;
        }

        public User GetUserById(int id)
        {
            var users = _context.Users.ToList().Where(u => u.id == id);
            User user = users.FirstOrDefault();
            return user;
        }

        public int Login(string username, string password)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(u => u.username.Equals(username));
            var user = users.FirstOrDefault();
            if(user != null)
            {
                if (!user.password.Equals(password))
                {
                    ind = -3;
                }
                else
                {
                    ind = user.id;
                }
            }  
            return ind;
            
        }

        public async Task<int> RegistrationAsync(User user)
        {
            int ind = -1;
            if(!_context.Users.Any(u => u.username.Equals(user.username)))
            {
                await _context.Users.AddAsync(user);
                ind = _context.SaveChanges();
            }
            return ind;
        }

    }
}
