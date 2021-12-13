using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;
using SkuciSeCode.Helpers;
using Microsoft.EntityFrameworkCore;

namespace SkuciSeCode.DAL
{
    public class UserDAL : IUserDAL
    {
        private readonly UserDbContext _context;
        public UserDAL(UserDbContext context)
        {
            _context = context;
        }
        public async Task<List<User>> GetAllUsers()
        {
            var list = await _context.Users.ToListAsync();
            return list;
        }

        public async Task<User> GetUserById(int id)
        {
            var users = await _context.Users.Where(u => u.id == id).ToListAsync();
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
                if(PasswordHelper.isValidPassword(user, password))
                {
                    ind = user.id;
                }
                else
                {
                    ind = -3;
                }
            }  
            return ind;
            
        }

        public async Task<int> RegistrationAsync(User user)
        {
            int ind = -3;
            if(!_context.Users.Any(u => u.email.Equals(user.email)))
            {
                if (!_context.Users.Any(u => u.username.Equals(user.username)))
                {
                    await _context.Users.AddAsync(user);
                    int ind1 = _context.SaveChanges();
                    if(ind1 > 0)
                    {
                        ind = user.id;
                    }
                    else
                    {
                        ind = ind1;
                    }
                }
                else
                {
                    ind = -1;
                }
            }
            
            return ind;
        }

        public int EditUser(int id, String username, String password, String name, String email, String image)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(user => user.id == id);
            User user = users.FirstOrDefault();
            if (user != null)
            {
                String userUsername = user.username;
                String userEmail = user.email;

                if (PasswordHelper.isValidPassword(user, password))
                {

                    var usersByUsername = _context.Users.ToList().Where(user => user.username == username);
                    var userByUsername = usersByUsername.FirstOrDefault();

                    if(userByUsername == null || (userByUsername != null && userByUsername.username == userUsername))
                    {

                        var usersByEmail = _context.Users.ToList().Where(user => user.email == email);
                        var userByEmail = usersByEmail.FirstOrDefault();

                        if(userByEmail == null || (userByEmail != null && userByEmail.email == userEmail))
                        {
                            user.username = username;
                            user.name = name;
                            user.email = email;
                            ind = _context.SaveChanges();

                            var userImages = _context.UserImages.ToList().Where(u => u.user_id == user.id);
                            UserImage userImg = userImages.FirstOrDefault();

                            if(userImg == null)
                            {
                                var indImage = this.SetProfilePicture(id, image);
                                if(ind == 0)
                                {
                                    ind = indImage.Result;
                                }
                            }

                            else if (!userImg.Equals(image) && image != "")
                            {
                                //UserImage userImage = new UserImage(user.id, image);
                                //_context.UserImages.Add(userImage);
                                //if(ind == 0)
                                //    ind = _context.SaveChanges();
                                userImg.image = image;
                                if(ind == 0)
                                {
                                    ind = _context.SaveChanges();
                                }
                            }
                        }
                        else
                        {
                            ind = -4;
                        }
                        

                    }
                    else
                    {
                        ind = -3;
                    }

                    
                }
                else
                {
                    ind = -2;
                }
            }
            return ind;
        }

        public int DeleteUser(int id)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(user => user.id == id);
            User user = users.FirstOrDefault();

            if (user != null)
            {
                _context.Users.Remove(user);
                ind = _context.SaveChanges();
            }

            return ind;
        }

        public int ChangePassword(int id, string hash, string salt)
        {
            int ind = -1;
            var users = _context.Users.ToList().Where(user => user.id == id);
            User user = users.FirstOrDefault();
            if(user != null)
            {
                user.hash = hash;
                user.salt = salt;
                ind = _context.SaveChanges();
            }
            return ind;
        }

        public async Task<UserImage> GetUserImage(int id)
        {
            var userImages = await _context.UserImages.Where(u => u.user_id == id).ToListAsync();
            UserImage userImage = userImages.FirstOrDefault();
            return userImage;
        }

        public async Task<int> SetProfilePicture(int user_id, string image)
        {
            UserImage userImage = new UserImage(user_id, image);
            await _context.UserImages.AddAsync(userImage);
            int ind = _context.SaveChanges();
            return ind;
        }
    }
}
