//
//  BDGlobalVC.m
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import "BDGlobalVC.h"
#import "BDStatusCell.h"
#import <FBSDKCoreKit/FBSDKCoreKit.h>
#import <FBSDKLoginKit/FBSDKLoginKit.h>
#import "Status.h"
#import "UIColor+flat.h"

@interface BDGlobalVC ()

@end

@implementation BDGlobalVC{
    BOOL isEnd;
    NSString *until;
    NSString *__paging_token;
    NSMutableArray *listData;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"BDStatusCell" bundle:nil] forCellReuseIdentifier:@"BDStatusCell"];
    self.tableView.estimatedRowHeight = 257;
    self.tableView.rowHeight = UITableViewAutomaticDimension;
    // Do any additional setup after loading the view from its nib.
    
    
    self.navigationItem.hidesBackButton = YES;
    UIImage* imageGlobal = [UIImage imageNamed:@"me"];
    CGRect frameimg = CGRectMake(0, 0, imageGlobal.size.width, imageGlobal.size.height);
    UIButton *btGlobal = [[UIButton alloc] initWithFrame:frameimg];
    [btGlobal setBackgroundImage:imageGlobal forState:UIControlStateNormal];
    [btGlobal addTarget:self action:@selector(btGlobalTouchUpInside)
       forControlEvents:UIControlEventTouchUpInside];
    [btGlobal setShowsTouchWhenHighlighted:YES];
    
    UIBarButtonItem *rightButton =[[UIBarButtonItem alloc] initWithCustomView:btGlobal];
    self.navigationItem.rightBarButtonItem=rightButton;
    isEnd = NO;
    __paging_token = @"";
    until = @"";
    listData = [[NSMutableArray alloc] init];
    [self getDataFromFacebook];

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



-(void) getDataFromFacebook{
    if (isEnd == YES) {
        return;
    }
    
    
    // request photo from fanpage.
    NSDictionary *params = @{
                             @"pretty":@"1",
                             @"limit":@"20",
                             @"until":until,
                             @"__paging_token":__paging_token};
    FBSDKGraphRequest *request = [[FBSDKGraphRequest alloc]
                                  initWithGraphPath: @"/1697920880476385/feed"
                                  parameters:params
                                  HTTPMethod:@"GET"];
    
    [request startWithCompletionHandler:^(FBSDKGraphRequestConnection *connection,
                                          id result,
                                          NSError *error) {
        if (!error && result){
            NSArray *postsData = [result objectForKey:@"data"];
            
            if ([postsData count] > 0)
            {
                if ([postsData count] < 25) {
                    isEnd = true;
                }
                for (NSDictionary *postInfo in postsData)
                {
                    if ([postInfo objectForKey:@"story"] != nil) {
                        continue;
                    }
                    else{
                        NSString *idPost = [postInfo objectForKey:@"id"];
                        
                        // request photo from fanpage.
                        NSDictionary *params2 = @{@"fields":@"full_picture,picture,message,actions,from"};
                        FBSDKGraphRequest *request2 = [[FBSDKGraphRequest alloc]
                                                       initWithGraphPath: [NSString stringWithFormat:@"/%@",idPost]
                                                       parameters:params2
                                                       HTTPMethod:@"GET"];
                        [request2 startWithCompletionHandler:^(FBSDKGraphRequestConnection *connection,
                                                               id result,
                                                               NSError *error) {
                            if ([result objectForKey:@"full_picture"] != [NSNull null]){                                                Status *dataImage = [[Status alloc] init];
                            
                                dataImage.likeNumber = @"0";
                                dataImage.commentNumber = @"0";
                                dataImage.caption = [result objectForKey:@"message"];
                                dataImage.timePost = [self catTime:(NSString *)[postInfo objectForKey:@"created_time"]];
                                dataImage.isShare = @"0";
                                dataImage.isComment = @"0";
                               
                                [listData addObject:dataImage];
                                [_tableView reloadData];

                            }
                        }];
                    }
                }
            }
            NSDictionary *paging =[result objectForKey:@"paging"];
            if ([paging objectForKey:@"next"] == [NSNull null]) {
                isEnd = YES;
                NSLog(@"\n\n\nXin thông báo 1 cháu hết!");
            }
            else{
                NSString *linkNextPhotos = [paging objectForKey:@"next"];
                NSArray *nextPageParamater = [linkNextPhotos componentsSeparatedByString: @"&"];
                NSMutableArray *tempArray = [[NSMutableArray alloc] init];
                NSString *tempPara = [[NSString alloc] init];
                for (int i=0; i<[nextPageParamater count]; i++) {
                    tempPara = [nextPageParamater objectAtIndex:i];
                    if ([tempPara rangeOfString:@"until"].location != NSNotFound) {
                        tempArray = [NSMutableArray arrayWithArray: [tempPara componentsSeparatedByString:@"="]];
                        until = [tempArray objectAtIndex:1];
                    }
                    if ([tempPara rangeOfString:@"__paging_token"].location != NSNotFound) {
                        tempArray = [NSMutableArray arrayWithArray: [tempPara componentsSeparatedByString:@"="]];
                        __paging_token = [tempArray objectAtIndex:1];
                    }
                }
            }
        }
        else{
            
        }
    }];
}


-(NSString*) catTime:(NSString*) time{
    NSArray *subStrings = [time componentsSeparatedByString:@"T"];
    NSArray *day =  [(NSString*)[subStrings objectAtIndex:0] componentsSeparatedByString:@"-"];
    NSString *strPrint = (NSString*)[day objectAtIndex:2];
    strPrint = [strPrint stringByAppendingString:@"-"];
    strPrint = [strPrint stringByAppendingString:(NSString*)[day objectAtIndex:1]];
    strPrint = [strPrint stringByAppendingString:@"-"];
    strPrint = [strPrint stringByAppendingString:(NSString*)[day objectAtIndex:0]];
    day =  [(NSString*)[subStrings objectAtIndex:1] componentsSeparatedByString:@":"];
    strPrint = [strPrint stringByAppendingString:@" | "];
    strPrint = [strPrint stringByAppendingString:(NSString*)[day objectAtIndex:0]];
    strPrint = [strPrint stringByAppendingString:@":"];
    strPrint = [strPrint stringByAppendingString:(NSString*)[day objectAtIndex:1]];
    
    
    
    return strPrint;
}



#pragma mark - Action
-(void)btGlobalTouchUpInside{
    [self.navigationController popViewControllerAnimated:YES];

}

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"Row pressed!!");
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return [listData count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    BDStatusCell *cell = [tableView dequeueReusableCellWithIdentifier:@"BDStatusCell"];
    [cell setSelectionStyle:UITableViewCellSelectionStyleNone];

    Status *st = [listData objectAtIndex:indexPath.row];
    cell.lbTime.text = st.timePost;
    cell.lbStatus.text = st.caption;
    cell.lbNumberLike.text = [st.likeNumber stringByAppendingString:@" Care"];
    cell.lbNumberCommnnt.text = [st.commentNumber stringByAppendingString:@" want to help"];
    if ([st.userLiked integerValue] == 1) {
        [cell.btLike setImage:[UIImage imageNamed:@"care2"] forState:UIControlStateNormal];
        cell.btLike.backgroundColor = [UIColor redColor];
    }
    else{
        [cell.btLike setImage:[UIImage imageNamed:@"care1"] forState:UIControlStateNormal];
        cell.btLike.backgroundColor = [UIColor colorWithHexCode:@"00A0B0"];
    }
    
    if ([st.isComment integerValue] == 1) {
        [cell.btComment setImage:[UIImage imageNamed:@"donate1"] forState:UIControlStateNormal];
        cell.btComment.backgroundColor = [UIColor redColor];
    }
    else{
        [cell.btComment setImage:[UIImage imageNamed:@"donate2"] forState:UIControlStateNormal];
        cell.btComment.backgroundColor = [UIColor colorWithHexCode:@"00A0B0"];
    }
    
    
    if ([st.isShare integerValue] == 1) {
        [cell.btShare setImage:[UIImage imageNamed:@"facebook1"] forState:UIControlStateNormal];
        cell.btShare.backgroundColor = [UIColor redColor];
    }
    else{
        [cell.btShare setImage:[UIImage imageNamed:@"facebook2"] forState:UIControlStateNormal];
        cell.btShare.backgroundColor = [UIColor colorWithHexCode:@"00A0B0"];
    }
    
    
    
    
    
    
    
    cell.btLike.tag = indexPath.row;
    [cell.btLike addTarget:self action:@selector(like:) forControlEvents:UIControlEventTouchUpInside];
    
    cell.btComment.tag = indexPath.row;
    [cell.btComment addTarget:self action:@selector(comment:) forControlEvents:UIControlEventTouchUpInside];
    
    cell.btShare.tag = indexPath.row;
    [cell.btShare addTarget:self action:@selector(share:) forControlEvents:UIControlEventTouchUpInside];

    if (indexPath.row == [listData count] - 2) {
        [self getDataFromFacebook];
    }
    
    
    return cell;

}

-(void)share:(UIButton*)sender{
    Status *st = [listData objectAtIndex: sender.tag ];
    if ([st.isShare integerValue] == 1) {
        st.isShare = @"0";
    }
    else{
        st.isShare = @"1";
    }
    [_tableView reloadData];
}

-(void)comment:(UIButton*)sender{
    Status *st = [listData objectAtIndex: sender.tag ];
    if ([st.isComment integerValue] == 1) {
        st.isComment = @"0";
    }
    else{
        st.isComment = @"1";
    }
    [_tableView reloadData];
}

-(void)like:(UIButton*)sender{
    Status *st = [listData objectAtIndex: sender.tag ];
    if ([st.userLiked integerValue] == 1) {
        st.userLiked = @"0";
    }
    else{
        st.userLiked = @"1";
    }
    [_tableView reloadData];
}



/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
